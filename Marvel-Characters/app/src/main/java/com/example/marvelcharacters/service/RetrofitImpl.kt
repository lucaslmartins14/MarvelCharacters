package com.example.marvelcharacters.service

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.example.marvelcharacters.model.Object
import com.example.marvelcharacters.ui.ComicsAdapter
import com.example.marvelcharacters.ui.MainActivity
import com.example.marvelcharacters.ui.SeriesAdapter
import com.example.marvelcharacters.utils.GenericCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

val apiKey = "9a6a48816b6cead69d35c04318b48680"
val hash = "43fdfbb0803d544709211281056ad94c"

lateinit var callComicsService: Call<Object>
lateinit var callSeriesService: Call<Object>
fun dialog(mainActivity: MainActivity, message: String) {
    val builder = AlertDialog.Builder(mainActivity)
    builder.setTitle("Alert!!")
    builder.setMessage(message)
    builder.setPositiveButton(android.R.string.yes) { dialog, which ->
        dialog.dismiss()
    }
    builder.show()
}

fun callCharacter(
    callback: GenericCallback<String>,
    progressBar: ProgressBar,
    characterName: String,
    seriesAdapter: SeriesAdapter,
    comicsAdapter: ComicsAdapter,
    tvName: TextView,
    tvDetailchar: TextView,
    ivThumbchar: ImageView,
    mainActivity: MainActivity
) {
    val callCharacterService =
        RetrofitInitializer("").characterService()!!.getCharacters(
            characterName, 1,
            apiKey,
            hash
        )
    progressBar.visibility = View.VISIBLE

    callCharacterService.clone().enqueue(object : Callback<Object> {
        override fun onResponse(call: Call<Object>, response: Response<Object>) {
            if (response.code() == 200) {
                Log.d("callbackcharacter", "worked!! " + response.message())
                response.let {
                    if (it.body()!!.data.results.size > 0) {
                        callComics(
                            callback,
                            it.body()!!.data.results.get(0).id.toString(),
                            seriesAdapter,
                            comicsAdapter
                        )
                        tvName.text = it.body()!!.data.results.get(0).name
                        tvDetailchar.text = it.body()!!.data.results.get(0).description
                        Glide.with(mainActivity).load(
                            it.body()!!.data.results.get(0).thumbnail.path + "/portrait_medium.${it.body()!!.data.results.get(
                                0
                            ).thumbnail.extension}"
                        ).centerCrop().override(100, 100).into(ivThumbchar)
                    } else {
                        callback.success = false
                        callback.call("error")
                        dialog(
                            mainActivity,
                            "Character not found!"
                        )
                    }

                }
            } else {
                callback.success = false
                callback.call("error")
                Log.d("callbackcharacter", "fail =( " + response.message())
                dialog(
                    mainActivity,
                    response.message()
                )
            }
        }

        override fun onFailure(call: Call<Object>, t: Throwable) {
            callback.success = false
            callback.call("error")
            Log.d("callbackcharacter", "fail =( " + t.message)
            dialog(
                mainActivity,
                t.message.toString()
            )
        }


    })
}

fun callComics(
    callback: GenericCallback<String>,
    characterId: String,
    seriesAdapter: SeriesAdapter,
    comicsAdapter: ComicsAdapter
) {
    callComicsService =
        RetrofitInitializer(characterId).comicsService()!!.getComics(
            1,
            apiKey,
            hash
        )

    callComicsService.clone().enqueue(object : Callback<Object> {
        override fun onResponse(call: Call<Object>, response: Response<Object>) {
            if (response.code() == 200) {
                response.let {
                    comicsAdapter.setItems(it.body()!!.data.results)
                    callSeries(
                        callback,
                        characterId,
                        seriesAdapter
                    )
                }
                Log.d("callbackcomics", "worked!! " + response.message())
            } else {
                callback.success = false
                callback.call("error")
                Log.d("callbackcomics", "fail =( " + response.message())
            }
        }

        override fun onFailure(call: Call<Object>, t: Throwable) {
            callback.success = false
            callback.call("error")
            Log.d("callbackcomics", "fail =( " + t.message)
        }

    })
}

fun callSeries(
    callback: GenericCallback<String>,
    characterId: String,
    seriesAdapter: SeriesAdapter
) {
    callSeriesService =
        RetrofitInitializer(characterId).seriesService()!!.getSeries(
            1,
            apiKey,
            hash
        )

    callSeriesService.clone().enqueue(object : Callback<Object> {
        override fun onResponse(call: Call<Object>, response: Response<Object>) {
            if (response.code() == 200) {
                response.let {
                    seriesAdapter.setItems(it.body()!!.data.results)
                }
                callback.success = true
                callback.call("success")
                Log.d("callbackseries", "worked!! " + response.message())
            } else {
                callback.success = false
                callback.call("error")
                Log.d("callbackseries", "fail =( " + response.message())
            }
        }

        override fun onFailure(call: Call<Object>, t: Throwable) {
            callback.success = false
            callback.call("error")
            Log.d("callbackseries", "fail =( " + t.message)
        }
    })
}