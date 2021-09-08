package com.example.virtustacktestapp.ui.main

import androidx.lifecycle.*
import com.example.virtustacktestapp.database.WordModel
import com.example.virtustacktestapp.database.WordRepository
import kotlinx.coroutines.launch
import kotlin.IllegalArgumentException

class WordViewModel(private val repository: WordRepository) : ViewModel() {
    fun getWordItems(): LiveData<List<WordModel>> {
        return repository.allWordItems.asLiveData()
    }
    
    fun addWordItem(wordItem: WordModel) = viewModelScope.launch { 
        repository.addWordItem(wordItem)
    }
}
