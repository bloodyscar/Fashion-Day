package com.example.fashionday.data.retrofit

import com.example.fashionday.data.response.BestTodayResponse
import com.example.fashionday.data.response.ResultPredictionResponse
import com.example.fashionday.data.response.SearchFashionResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @Multipart
    @POST("upload-file")
    fun postSearch(
        @Part gambar: MultipartBody.Part,
        @Part("gender") gender: RequestBody
    ): Call<ResultPredictionResponse>

    @GET("best-today")
    fun getBestToday() : Call<BestTodayResponse>


}

