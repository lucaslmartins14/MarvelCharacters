package com.example.marvelcharacters.model

import androidx.lifecycle.LiveData
import com.google.gson.annotations.Expose

class Data
{
    @Expose
    var results = listOf<Results>()
}