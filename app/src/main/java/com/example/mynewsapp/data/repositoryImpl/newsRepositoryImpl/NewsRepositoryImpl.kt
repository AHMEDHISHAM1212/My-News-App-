package com.example.mynewsapp.data.repositoryImpl.newsRepositoryImpl

import com.example.mynewsapp.data.api.model.newsResponse.NewsItem
import com.example.mynewsapp.dataSource.NewsDataSource.NewsDataSource
import com.example.mynewsapp.repository.newsRepository.NewSourcesRepository

class NewsRepositoryImpl(private val newsOnlineDataSource: NewsDataSource)
    : NewSourcesRepository {
    override suspend fun getNews(sourceId: String): List<NewsItem?>? {
        return newsOnlineDataSource.getNews(sourceId)
    }
}