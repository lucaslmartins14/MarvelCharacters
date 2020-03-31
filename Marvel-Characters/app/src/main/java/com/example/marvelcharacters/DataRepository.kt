package com.example.marvelcharacters

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.marvelcharacters.model.Results

data class DataRepository(var application: Application) {
    var comics: List<Results> = listOf()

}