package com.example.mynewsapp.data.dataSourceImpl.newsDataSourceImpl

import com.example.mynewsapp.data.api.WebServices
import com.example.mynewsapp.data.api.model.newsResponse.NewsItem
import com.example.mynewsapp.dataSource.NewsDataSource.NewsDataSource
import javax.inject.Inject

class NewsOnlineDataSourceImpl @Inject constructor(private val webServices: WebServices) :
    NewsDataSource {
    override suspend fun getNews(sourceId: String): List<NewsItem?>? {
        val news = webServices.getNews(sources = sourceId)
        return news.articles
    }
}