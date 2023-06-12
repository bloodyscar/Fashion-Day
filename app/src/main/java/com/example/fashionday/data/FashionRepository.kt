package com.example.fashionday.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.fashionday.data.response.BestTodayResponse
import com.example.fashionday.data.response.DataItem
import com.example.fashionday.data.response.ResultPredictionResponse
import com.example.fashionday.data.response.SearchFashionResponse
import com.example.fashionday.data.retrofit.ApiService
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FashionRepository(private val apiService: ApiService) {

    fun getBestToday(): LiveData<Result<List<DataItem>>> {
        val listBest = MediatorLiveData<Result<List<DataItem>>>()

        listBest.postValue(Result.Loading)

        var client = apiService.getBestToday()
        client.enqueue(object : Callback<BestTodayResponse> {
            override fun onResponse(
                call: Call<BestTodayResponse>,
                response: Response<BestTodayResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    Log.d("ADABWANG", responseBody.toString())
                    if (responseBody != null) {
                        listBest.value = Result.Success(responseBody.data)
                    }

                } else {
                    Log.d("A2DABWANG", "ERRRPR BWANG")
                }
            }

            override fun onFailure(call: Call<BestTodayResponse>, t: Throwable) {
                Log.e("A2DABWANG", "onFailure: ${t.message}")
            }
        })

        return listBest
    }

    fun postSearchImage(
        gambar: MultipartBody.Part,
        gender: RequestBody,
    ): LiveData<Result<ResultPredictionResponse>> {
        val listSearch = MediatorLiveData<Result<ResultPredictionResponse>>()

        listSearch.postValue(Result.Loading)

        var client = apiService.postSearch(gambar, gender)

        client.enqueue(object : Callback<ResultPredictionResponse> {
            override fun onResponse(
                call: Call<ResultPredictionResponse>,
                response: Response<ResultPredictionResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    Log.d("ADABWANG", responseBody.toString())
                    if (responseBody != null) {
                        listSearch.value = Result.Success(responseBody)
                    }

                } else {
                    Log.d("A2DABWANG", "ERRRPR BWANG")
                }
            }

            override fun onFailure(call: Call<ResultPredictionResponse>, t: Throwable) {
                Log.d("A2DABWANG", t.message.toString())
            }

        })

        return listSearch
    }

    companion object {
        @Volatile
        private var instance: FashionRepository? = null
        fun getInstance(
            apiService: ApiService
        ): FashionRepository =
            instance ?: synchronized(this) {
                instance ?: FashionRepository(apiService)
            }.also { instance = it }
    }

}