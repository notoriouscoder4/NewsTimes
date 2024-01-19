package dev.notoriouscoder4.newstimes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.notoriouscoder4.newstimes.data.database.dao.ArticleDao
import dev.notoriouscoder4.newstimes.data.database.dao.SavedArticleDao
import dev.notoriouscoder4.newstimes.data.database.entity.Article
import dev.notoriouscoder4.newstimes.data.database.entity.SavedArticleEntity

@Database(entities = [SavedArticleEntity::class, Article::class], version = 1, exportSchema = false)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun getSavedArticleDao(): SavedArticleDao
    abstract fun getArticleDao(): ArticleDao
}