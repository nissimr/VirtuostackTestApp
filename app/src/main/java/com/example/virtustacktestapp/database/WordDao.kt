package com.example.virtustacktestapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Query("SELECT * FROM word_table")
    fun getWordItems(): Flow<List<WordModel>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWordItem(wordModel: WordModel)
}