package dev.notoriouscoder4.newstimes

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import dagger.hilt.android.HiltAndroidApp
import dev.notoriouscoder4.newstimes.common.Const
import dev.notoriouscoder4.newstimes.common.util.TimeUtil
import dev.notoriouscoder4.newstimes.worker.NewsTimesWorker
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class NewsTimesApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    lateinit var workManager: WorkManager

    override fun onCreate() {
        super.onCreate()
        initWorkManager()
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    private fun initWorkManager() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest = PeriodicWorkRequest.Builder(
            NewsTimesWorker::class.java,
            24,
            TimeUnit.HOURS
        ).setConstraints(constraints)
            .setInitialDelay(TimeUtil.getInitialDelay(), TimeUnit.MILLISECONDS)
            .addTag(Const.UNIQUE_WORK_NAME)
            .build()

        workManager.enqueueUniquePeriodicWork(
            Const.UNIQUE_WORK_NAME,
            ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE, workRequest
        )
    }
}