package com.example.mynewsapp.data.repositoryImpl.sourcesRepositoryImpl

import com.example.mynewsapp.data.api.model.sourcesResponse.Source
import com.example.mynewsapp.dataSource.sourcesDataSource.SourcesDataSource
import com.example.mynewsapp.repository.sourcesRepository.SourcesRepository
import com.example.mynewsapp.ui.home.category.Category

class SourcesRepositoryImpl(private val sourcesDataSource: SourcesDataSource): SourcesRepository {
    override suspend fun getSources(category: Category): List<Source?>? {
        return sourcesDataSource.getSources(category)
    }

}