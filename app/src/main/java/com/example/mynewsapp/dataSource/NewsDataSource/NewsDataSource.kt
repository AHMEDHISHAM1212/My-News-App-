package com.example.mynewsapp.dataSource.NewsDataSource

import com.example.mynewsapp.data.api.model.newsResponse.NewsItem

interface NewsDataSource {
    suspend fun getNews(sourceId: String): List<NewsItem?>?
}