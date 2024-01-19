package dev.notoriouscoder4.newstimes.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dev.notoriouscoder4.newstimes.R
import dev.notoriouscoder4.newstimes.common.Const
import dev.notoriouscoder4.newstimes.common.Const.NOTIFICATION_ID
import dev.notoriouscoder4.newstimes.common.util.apiArticleListToArticleList
import dev.notoriouscoder4.newstimes.data.database.DatabaseService
import dev.notoriouscoder4.newstimes.data.network.ApiInterface
import dev.notoriouscoder4.newstimes.ui.NewsTimesActivity

@HiltWorker
class NewsTimesWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workerParameters: WorkerParameters,
    private val database: DatabaseService,
    private val network: ApiInterface
) : CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {
        lateinit var result: Result
        kotlin.runCatching {
            val articles = network.getNews().articles.apiArticleListToArticleList()
            database.deleteAllAndInsertAll(articles)
        }.onSuccess {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                sendNotification()
            }
            result = Result.success()
        }.onFailure {
            result = Result.retry()
        }
        return result
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun sendNotification() {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create a notification channel
        val channel = NotificationChannel(
            Const.NOTIFICATION_CHANNEL_ID,
            Const.NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)

        // Create an Intent for the NewsActivity
        val intent = Intent(applicationContext, NewsTimesActivity::class.java)
        intent.flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtra("notification_id", NOTIFICATION_ID)

        // Create a PendingIntent
        val pendingIntent = getActivity(
            applicationContext,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        // Build the notification
        val notification =
            NotificationCompat.Builder(applicationContext, Const.NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText(Const.NOTIFICATION_CONTENT_TEXT)
                .setContentTitle(Const.NOTIFICATION_CONTENT_TITLE)
                .setContentIntent(pendingIntent)
                .build()

        // Show the notification
        notificationManager.notify(NOTIFICATION_ID, notification)
    }
}