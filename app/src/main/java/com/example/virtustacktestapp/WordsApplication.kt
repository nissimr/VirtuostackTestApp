package com.example.virtustacktestapp

import android.app.Application
import com.example.virtustacktestapp.database.WordDatabase
import com.example.virtustacktestapp.database.WordRepository
import com.example.virtustacktestapp.ui.main.WordViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WordsApplication : Application() {
    companion object {
        private lateinit var wordsApplication: WordsApplication
        private lateinit var wordViewModel: WordViewModel
        
        fun injectWordViewModel() = wordViewModel
    }

    override fun onCreate() {
        super.onCreate()
        val applicationScope = CoroutineScope(SupervisorJob())

        val database by lazy { WordDatabase.getDatabase(this, applicationScope) }
        val repository by lazy { WordRepository(database.wordDao()) }
        
        wordsApplication = WordsApplication()
        wordViewModel = WordViewModel(repository)
    }
}