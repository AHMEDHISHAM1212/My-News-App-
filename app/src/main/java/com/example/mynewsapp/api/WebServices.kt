package com.example.mynewsapp.api

import com.example.mynewsapp.api.model.newsResponse.NewsResponse
import com.example.mynewsapp.api.model.sourcesResponse.Source
import com.example.mynewsapp.api.model.sourcesResponse.SourcesResponse
import com.example.mynewsapp.constant.APiConstant
import com.example.mynewsapp.ui.home.category.Category
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/top-headlines/sources?apiKey=8ed6ad8e5a074ebb8b18406949c8cf61
interface WebServices {
    @GET("v2/top-headlines/sources")
    fun getSources(
        @Query("apiKey")apiKey: String=APiConstant.API_KEY,
        @Query("category") category: String
    ): Call<SourcesResponse>

    @GET("v2/everything")
    fun getNews(
        @Query("apiKey")apiKey: String= APiConstant.API_KEY,
        @Query("sources")sources: String
    ):Call<NewsResponse>
}