package dev.notoriouscoder4.newstimes.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import dagger.hilt.android.AndroidEntryPoint
import dev.notoriouscoder4.newstimes.ui.base.NewsTimesNavHost
import dev.notoriouscoder4.newstimes.ui.theme.NewsTimesTheme

@AndroidEntryPoint
class NewsTimesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsTimesTheme {
                // A surface container using the 'background' color from the theme
                Surface {
                    NewsTimesNavHost()
                }
            }
        }
    }
}