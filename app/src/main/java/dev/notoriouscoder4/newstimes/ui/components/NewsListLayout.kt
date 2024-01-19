package dev.notoriouscoder4.newstimes.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import dev.notoriouscoder4.newstimes.data.database.entity.Article

@Composable
fun NewsLayout(
    newsList: List<Article>,
    articleClicked: (Article) -> Unit
) {
    LazyColumn {
        items(newsList) {
            Article(it) { article ->
                articleClicked(article)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsLayoutWithDelete(
    newsList: List<Article>,
    articleClicked: (Article) -> Unit,
    deleteArticle: (Article) -> Unit
) {
    LazyColumn {
        items(newsList, key = { article -> article.url!! }) { article ->
            val dismissState = rememberDismissState()
            if (dismissState.isDismissed(direction = DismissDirection.EndToStart) || dismissState.isDismissed(
                    direction = DismissDirection.StartToEnd
                )
            ) {
                deleteArticle(article)
            }
            SwipeToDismiss(
                state = dismissState,
                background = {},
                dismissContent = {
                    Article(article) {
                        articleClicked(it)
                    }
                },
            )
        }
    }
}