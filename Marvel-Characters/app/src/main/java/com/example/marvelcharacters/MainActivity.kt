package com.example.marvelcharacters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val apiKey = "9a6a48816b6cead69d35c04318b48680"
        val hash = "43fdfbb0803d544709211281056ad94c"
        val callCharacterService = RetrofitInitializer().characterService()!!.getCharacters("spider-man", 1,apiKey, hash)

        callCharacterService.clone().enqueue(object : Callback<Object> {
            override fun onResponse(call: Call<Object>, response: Response<Object>) {
                Log.d("callbacksons", "deu certo " + response.message())
            }

            override fun onFailure(call: Call<Object>, t: Throwable) {
                Log.d("callbacksons", "deu errado " + t.message)
            }


        })
    }
}
