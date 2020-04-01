package com.example.marvelcharacters.service

import com.example.marvelcharacters.model.Object
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

    @GET("characters")
    fun getCharacters(
        @Query("name") name: String,
        @Query("ts") ts: Int,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): Call<Object>

    @GET("comics")
    fun getComics(
        @Query("ts") ts: Int,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): Call<Object>

    @GET("series")
    fun getSeries(
        @Query("ts") ts: Int,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): Call<Object>


}