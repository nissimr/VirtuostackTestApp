package com.example.virtustacktestapp.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {
    val allWordItems: Flow<List<WordModel>> = wordDao.getWordItems()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun addWordItem(wordModel: WordModel) {
        wordDao.addWordItem(wordModel)
    }
}