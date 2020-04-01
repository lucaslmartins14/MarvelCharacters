package com.example.marvelcharacters.ui

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.marvelcharacters.utils.GenericCallback
import com.example.marvelcharacters.R
import com.example.marvelcharacters.utils.Utils
import com.example.marvelcharacters.service.callCharacter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var layoutManagerComics: LinearLayoutManager
    lateinit var layoutManagerSeries: LinearLayoutManager
    lateinit var seriesAdapter: SeriesAdapter
    lateinit var comicsAdapter: ComicsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        scrollView.visibility = View.GONE
        val callback = object : GenericCallback<String>() {
            override fun call(result: String) {
                progressBar.visibility = View.GONE
                if (result == "success") {
                    scrollView.visibility = View.VISIBLE
                } else {
                    scrollView.visibility = View.GONE
                }
            }
        }
        layoutManagerComics = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        layoutManagerSeries = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        (recyclerview_comics.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        (recyclerview_series.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        recyclerview_comics.layoutManager = layoutManagerComics
        recyclerview_series.layoutManager = layoutManagerSeries
        seriesAdapter = SeriesAdapter(this)
        comicsAdapter = ComicsAdapter(this)
        recyclerview_comics.adapter = comicsAdapter
        recyclerview_series.adapter = seriesAdapter
        et_search.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                scrollView.visibility = View.GONE
                Utils.hideSoftKeyBoard(this@MainActivity, v)
                callCharacter(
                    callback,
                    progressBar,
                    et_search.text.toString(),
                    seriesAdapter,
                    comicsAdapter,
                    tv_name,
                    tv_detailchar,
                    iv_thumbchar,
                    this
                )
                true
            } else {
                false
            }
        }

    }

}
