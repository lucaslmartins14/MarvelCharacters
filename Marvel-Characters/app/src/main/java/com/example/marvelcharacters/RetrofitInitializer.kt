package com.example.marvelcharacters

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    val retrofit = Retrofit.Builder().baseUrl("https://gateway.marvel.com/v1/public/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    fun characterService(): CharacterService? = retrofit.create(CharacterService::class.java)

}