package com.example.mynewsapp.repository.newsRepository

import com.example.mynewsapp.data.api.model.newsResponse.NewsItem

interface NewSourcesRepository {
    suspend fun getNews(sourceId: String): List<NewsItem?>?
}