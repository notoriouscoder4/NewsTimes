package dev.notoriouscoder4.newstimes.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.notoriouscoder4.newstimes.common.dispatcher.DispatcherProvider
import dev.notoriouscoder4.newstimes.data.database.entity.Article
import dev.notoriouscoder4.newstimes.data.repository.NewsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {
    fun saveArticle(article: Article) = viewModelScope.launch(dispatcherProvider.io) {
        newsRepository.upsert(article)
    }

    fun deleteArticle(article: Article) = viewModelScope.launch(dispatcherProvider.io) {
        newsRepository.deleteArticle(article)
    }

    fun getSavedNews() = newsRepository.getSavedNews()
}