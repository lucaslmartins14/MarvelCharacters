package com.example.marvelcharacters.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer(characterId: String) {

    val retrofitCharacter = Retrofit.Builder().baseUrl("https://gateway.marvel.com/v1/public/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    fun characterService(): CharacterService? = retrofitCharacter.create(
        CharacterService::class.java
    )

    val retrofitComics =
        Retrofit.Builder().baseUrl("https://gateway.marvel.com/v1/public/characters/$characterId/")
            .addConverterFactory(GsonConverterFactory.create()).build()

    fun comicsService(): CharacterService? = retrofitComics.create(
        CharacterService::class.java
    )

    val retrofitSeries =
        Retrofit.Builder().baseUrl("https://gateway.marvel.com/v1/public/characters/$characterId/")
            .addConverterFactory(GsonConverterFactory.create()).build()

    fun seriesService(): CharacterService? = retrofitSeries.create(
        CharacterService::class.java
    )
}