package com.example.virtustacktestapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [WordModel::class], version = 1, exportSchema = false)
abstract class WordDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
    
    companion object {
        /**
         * singleton to prevent multiple instances of db
         */
        @Volatile
        private var INSTANCE: WordDatabase? = null
        
        fun getDatabase(context: Context, scope: CoroutineScope): WordDatabase {
            /**
             * if INSTANCE is not null, return it, otherwise create db
             */
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, WordDatabase::class.java, "word_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                /**
                 * return instance
                 */
                instance
            }
        }
        
        private class WordDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onCreate method to populate the database
             */
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) { 
                        populateDatabase(database.wordDao())
                    }
                }
            }
        }
        
        suspend fun populateDatabase(wordDao: WordDao) {
            var wordItem = WordModel("Alpha", 1)
            wordDao.addWordItem(wordItem)
            
            wordItem = WordModel("Beta", -1)
            wordDao.addWordItem(wordItem)
            
            wordItem = WordModel("Cat", 1)
            wordDao.addWordItem(wordItem)
            
            wordItem = WordModel("Doremon", -1)
            wordDao.addWordItem(wordItem)
            
            wordItem = WordModel("Elephant", 1)
            wordDao.addWordItem(wordItem)
            
            wordItem = WordModel("Fantom", -1)
            wordDao.addWordItem(wordItem)
            
            wordItem = WordModel("Goal", 1)
            wordDao.addWordItem(wordItem)
            
            wordItem = WordModel("Helina", -1)
            wordDao.addWordItem(wordItem)
            
            wordItem = WordModel("India", 1)
            wordDao.addWordItem(wordItem)
            
            wordItem = WordModel("Jem", -1)
            wordDao.addWordItem(wordItem)
            
            wordItem = WordModel("Kite", 1)
            wordDao.addWordItem(wordItem)
            
            wordItem = WordModel("Local", -1)
            wordDao.addWordItem(wordItem)
            
            wordItem = WordModel("Machine", 1)
            wordDao.addWordItem(wordItem)
            
            wordItem = WordModel("Note", -1)
            wordDao.addWordItem(wordItem)
        }
    }
}