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

class ComicsViewModel : ViewModel() {
    private var allComics = MutableLiveData<List<Results>>()
    fun getallComics(): LiveData<List<Results>> = allComics

    fun loadComics(comics: MutableLiveData<List<Results>>){
        allComics = comics
        allComics.mutation { it ->
            it.postValue(comics.value)
        }
    }

    fun <T> MutableLiveData<T>.mutation(actions: (MutableLiveData<T>) -> Unit) {
        actions(this)
        this.value = this.value
    }
}