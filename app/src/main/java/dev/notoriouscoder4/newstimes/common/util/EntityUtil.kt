package dev.notoriouscoder4.newstimes.common.util

import dev.notoriouscoder4.newstimes.data.database.entity.Article
import dev.notoriouscoder4.newstimes.data.database.entity.SavedArticleEntity
import dev.notoriouscoder4.newstimes.data.database.entity.SavedSourceEntity
import dev.notoriouscoder4.newstimes.data.database.entity.Source
import dev.notoriouscoder4.newstimes.data.model.ApiArticle
import dev.notoriouscoder4.newstimes.data.model.ApiSource

fun Article.articleToSavedArticleEntity(): SavedArticleEntity {
    return SavedArticleEntity(
        id = id,
        source = source.sourceToSavedSourceEntity(),
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content
    )
}

fun SavedArticleEntity.savedArticleEntityToArticle(): Article {
    return Article(
        id = id,
        source = source.savedSourceEntityToSource(),
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content
    )
}

fun Source.sourceToSavedSourceEntity(): SavedSourceEntity {
    return SavedSourceEntity(
        id = id,
        name = name
    )
}

fun SavedSourceEntity.savedSourceEntityToSource(): Source {
    return Source(
        id = id,
        name = name
    )
}

fun ApiArticle.apiArticleToArticle(): Article {
    return Article(
        source = source,
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content
    )
}

fun List<ApiArticle>.apiArticleListToArticleList(): List<Article> {
    val list = mutableListOf<Article>()
    forEach { apiArticle ->
        list.add(apiArticle.apiArticleToArticle())
    }
    return list
}

fun ApiSource.apiSourceToSource(): Source {
    return Source(
        id = id,
        name = name
    )
}

fun List<ApiSource>.apiSourceListToSourceList(): List<Source> {
    val list = mutableListOf<Source>()
    forEach { apiSource ->
        list.add(apiSource.apiSourceToSource())
    }
    return list
}