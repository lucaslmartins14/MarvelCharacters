package com.example.marvelcharacters

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.MutableLiveData
import com.example.marvelcharacters.model.Object
import com.example.marvelcharacters.model.Results
import com.example.marvelcharacters.ui.RepositoryAdapter
import com.example.marvelcharacters.viewmodel.ComicsViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
val apiKey = "9a6a48816b6cead69d35c04318b48680"
val hash = "43fdfbb0803d544709211281056ad94c"
val callCharacterService = RetrofitInitializer("").characterService()!!.getCharacters("spider-man", 1,apiKey, hash)
val mComics: MutableLiveData<List<Results>> = MutableLiveData()
lateinit var callComicsService: Call<Object>
lateinit var callSeriesService: Call<Object>

fun callCharacter(
    callback: GenericCallback<String>,
    progressBar: ProgressBar,
    adapter: RepositoryAdapter
){
    progressBar.visibility = View.VISIBLE

    callCharacterService.clone().enqueue(object : Callback<Object> {
        override fun onResponse(call: Call<Object>, response: Response<Object>) {
            if (response.code() == 200){
                Log.d("callbacksons", "deu certo " + response.message())
                response.let {
                    callComics(callback, it.body()!!.data.results.get(0).id.toString(), adapter)
                }

            }else{
                callback.success = false
                callback.call("error")
                Log.d("callbacksons", "deu errado " + response.message())
            }

        }

        override fun onFailure(call: Call<Object>, t: Throwable) {
            callback.success = false
            callback.call("error")
            Log.d("callbacksons", "deu errado " + t.message)
        }


    })
}
fun callComics(
    callback: GenericCallback<String>,
    characterId: String,
    adapter: RepositoryAdapter
){
    callComicsService = RetrofitInitializer(characterId).comicsService()!!.getComics(1, apiKey, hash)

    callComicsService.clone().enqueue(object : Callback<Object> {
        override fun onResponse(call: Call<Object>, response: Response<Object>) {
            if (response.code() == 200){
                response.let {
                    adapter.setItems(it.body()!!.data.results)
                    mComics.postValue(it.body()!!.data.results)
                    ComicsViewModel().loadComics(mComics)
                   DataRepository(Application()).comics = it.body()!!.data.results
                    callSeries(callback, characterId)
                }
                Log.d("callbacksons", "deu certo " + response.message())
            }else{
                callback.success = false
                callback.call("error")
                Log.d("callbacksons", "deu errado " + response.message())
            }
        }

        override fun onFailure(call: Call<Object>, t: Throwable) {
            callback.success = false
            callback.call("error")
            Log.d("callbacksons", "deu errado " + t.message)
        }

    })
}
fun callSeries(callback: GenericCallback<String>, characterId: String){
    callSeriesService = RetrofitInitializer(characterId).seriesService()!!.getSeries(1, apiKey, hash)

    callSeriesService.clone().enqueue(object : Callback<Object> {
        override fun onResponse(call: Call<Object>, response: Response<Object>) {
            if (response.code() == 200){
                callback.success = true
                callback.call("success")
                Log.d("callbacksons", "deu certo " + response.message())
            }else{
                callback.success = false
                callback.call("error")
                Log.d("callbacksons", "deu errado " + response.message())
            }
        }

        override fun onFailure(call: Call<Object>, t: Throwable) {
            callback.success = false
            callback.call("error")
            Log.d("callbacksons", "deu errado " + t.message)
        }

    })
}