package com.example.mynewsapp.api

import com.example.mynewsapp.api.model.SourcesResponse
import com.example.mynewsapp.constant.APiConstant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/top-headlines/sources?apiKey=8ed6ad8e5a074ebb8b18406949c8cf61
interface WebServices {
    @GET("v2/top-headlines/sources")
    fun getSources(
        @Query("apiKey")apiKey: String=APiConstant.API_KEY
    ): Call<SourcesResponse>
}