package com.example.mynewsapp.data.repositoryImpl.newsRepositoryImpl

import com.example.mynewsapp.data.api.model.newsResponse.NewsItem
import com.example.mynewsapp.dataSource.NewsDataSource.NewsDataSource
import com.example.mynewsapp.repository.newsRepository.NewRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val newsOnlineDataSource: NewsDataSource)
    : NewRepository {
    override suspend fun getNews(sourceId: String): List<NewsItem?>? {
        return newsOnlineDataSource.getNews(sourceId)
    }
}