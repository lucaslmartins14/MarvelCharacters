package com.example.marvelcharacters

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

    @GET("characters")
    fun getCharacters(@Query("name") name: String,
                      @Query("ts") ts: Int,
                      @Query("apikey") apikey: String,
                      @Query("hash") hash: String): Call<Object>
}