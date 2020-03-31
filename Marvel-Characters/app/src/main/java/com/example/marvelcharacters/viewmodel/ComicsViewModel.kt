package com.example.marvelcharacters.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.marvelcharacters.DataRepository
import com.example.marvelcharacters.model.Data
import com.example.marvelcharacters.model.Results
import kotlinx.coroutines.Dispatchers

class ComicsViewModel() : ViewModel() {
    internal var allComics: MutableLiveData<List<Results>> = MutableLiveData()
    init {
        allComics.value = DataRepository().comics
    }

    fun setComics(comics: List<Results>){
        allComics.value = comics
    }

}