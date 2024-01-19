package dev.notoriouscoder4.newstimes.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import dev.notoriouscoder4.newstimes.R
import dev.notoriouscoder4.newstimes.data.database.entity.Article
import dev.notoriouscoder4.newstimes.ui.base.ShowError
import dev.notoriouscoder4.newstimes.ui.base.WebViewPage
import dev.notoriouscoder4.newstimes.ui.viewmodels.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleScreen(
    article: Article?,
    sharedViewModel: SharedViewModel = hiltViewModel()
) {
    val mContext = LocalContext.current

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (article != null) {
                    sharedViewModel.saveArticle(article)
                }
                Toast.makeText(
                    mContext,
                    mContext.resources.getString(R.string.article_saved_successfully),
                    Toast.LENGTH_SHORT
                ).show()
            }) {
                Icon(painter = painterResource(id = R.drawable.ic_save), contentDescription = null)
            }
        }
    ) {
        if (article?.url == null) {
            ShowError(text = stringResource(id = R.string.something_went_wrong))
        } else {
            WebViewPage(
                url = article.url,
                modifier = Modifier.padding(it)
            )
        }
    }
}