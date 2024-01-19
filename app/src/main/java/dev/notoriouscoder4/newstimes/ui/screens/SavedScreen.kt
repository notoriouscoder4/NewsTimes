package dev.notoriouscoder4.newstimes.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.notoriouscoder4.newstimes.R
import dev.notoriouscoder4.newstimes.data.database.entity.Article
import dev.notoriouscoder4.newstimes.ui.base.ShowError
import dev.notoriouscoder4.newstimes.ui.components.NewsLayoutWithDelete
import dev.notoriouscoder4.newstimes.ui.viewmodels.SharedViewModel

@Composable
fun SavedScreen(
    sharedViewModel: SharedViewModel = hiltViewModel(),
    newsClicked: (Article) -> Unit
) {
    val newsList: List<Article> by sharedViewModel.getSavedNews()
        .collectAsStateWithLifecycle(emptyList())

    if (newsList.isEmpty()) {
        ShowError(text = stringResource(R.string.no_saved_news))
    } else {
        NewsLayoutWithDelete(newsList = newsList,
            articleClicked = {
                newsClicked(it)
            }) {
            sharedViewModel.deleteArticle(it)
        }
    }
}