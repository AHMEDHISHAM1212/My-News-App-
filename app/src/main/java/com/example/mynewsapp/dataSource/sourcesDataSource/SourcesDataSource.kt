package com.example.mynewsapp.dataSource.sourcesDataSource

import com.example.mynewsapp.data.api.model.sourcesResponse.Source
import com.example.mynewsapp.ui.home.category.Category

interface SourcesDataSource {
    suspend fun getSources(category: Category): List<Source?>?
}