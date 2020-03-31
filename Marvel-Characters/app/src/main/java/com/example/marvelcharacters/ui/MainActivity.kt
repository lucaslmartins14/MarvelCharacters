package com.example.marvelcharacters.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.marvelcharacters.GenericCallback
import com.example.marvelcharacters.R
import com.example.marvelcharacters.callCharacter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: RepositoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val callback = object : GenericCallback<String>(){
            override fun call(result: String) {
                progressBar.visibility = View.GONE
            }
        }



        layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        (recyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        recyclerView.layoutManager = layoutManager
        adapter = RepositoryAdapter(this)
        recyclerView.adapter = adapter
//        ComicsViewModel().getallComics().observe(this, androidx.lifecycle.Observer { comics ->
//            adapter.setItems(comics)
//        })
        callCharacter(callback, progressBar, adapter)
    }
}
