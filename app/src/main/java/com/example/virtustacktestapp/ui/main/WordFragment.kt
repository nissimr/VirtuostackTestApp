package com.example.virtustacktestapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.virtustacktestapp.R
import com.example.virtustacktestapp.WordsApplication
import com.example.virtustacktestapp.database.WordModel
import kotlinx.android.synthetic.main.fragment_word.*

class WordFragment : Fragment() {
    private lateinit var wordListData: List<WordModel>
    private var wordViewAdapter: WordViewAdapter? = null
    private val wordViewModel = WordsApplication.injectWordViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_word, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wordViewModel.getWordItems()
            .observe(viewLifecycleOwner, { wordList ->
                wordViewAdapter = WordViewAdapter(context, wordList)
                word_recycler_view?.layoutManager = LinearLayoutManager(context)
                word_recycler_view?.adapter = wordViewAdapter
                wordListData = wordList
            })
        
        val fab: View = requireView().findViewById(R.id.fab)
        fab.setOnClickListener {
            for (item in wordListData) {
                item.word_color = item.word_color * -1
                wordViewAdapter?.notifyDataSetChanged()
            }
        }
    }
    
    companion object {
        fun newInstance() = WordFragment()
    }
}