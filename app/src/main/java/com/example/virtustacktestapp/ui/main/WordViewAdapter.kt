package com.example.virtustacktestapp.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.virtustacktestapp.R
import com.example.virtustacktestapp.database.WordModel

class WordViewAdapter(
    var context: Context?,
    var wordListItem: List<WordModel>
) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rootView: View = LayoutInflater.from(context).inflate(R.layout.item_word, parent, false)
        return RecyclerViewViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = wordListItem[position]
        val viewHolder = holder as RecyclerViewViewHolder
        viewHolder.wordText.text = item.word_name
        if (item.word_color == 1)
            viewHolder.cardView.setCardBackgroundColor(ContextCompat.getColor(context!!, R.color.red))
        else if (item.word_color == -1)
            viewHolder.cardView.setCardBackgroundColor(ContextCompat.getColor(context!!, R.color.green))
    }

    override fun getItemCount(): Int {
        return wordListItem.size
    }
    
    internal inner class RecyclerViewViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
                var wordText: TextView = itemView.findViewById(R.id.word_name)
                var cardView: CardView = itemView.findViewById(R.id.main_card_view)
            }
}