package com.example.mynewsapp.data.api

import com.example.mynewsapp.data.api.model.newsResponse.NewsResponse
import com.example.mynewsapp.data.api.model.sourcesResponse.Source
import com.example.mynewsapp.data.api.model.sourcesResponse.SourcesResponse
import com.example.mynewsapp.constant.APiConstant
import com.example.mynewsapp.ui.home.category.Category
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/top-headlines/sources?apiKey=8ed6ad8e5a074ebb8b18406949c8cf61
interface WebServices {
    @GET("v2/top-headlines/sources")
    suspend fun getSources(
        @Query("apiKey")apiKey: String=APiConstant.API_KEY,
        @Query("category") category: String
    ): SourcesResponse

    @GET("v2/everything")
    suspend fun getNews(
        @Query("apiKey")apiKey: String= APiConstant.API_KEY,
        @Query("sources")sources: String
    ): NewsResponse
}