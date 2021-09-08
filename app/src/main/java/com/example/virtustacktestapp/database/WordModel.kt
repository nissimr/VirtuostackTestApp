package com.example.virtustacktestapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
data class WordModel(@PrimaryKey @ColumnInfo(name = "word_name") val word_name: String,
                     @ColumnInfo(name = "word_color") var word_color: Int)