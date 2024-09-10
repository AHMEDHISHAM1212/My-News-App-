package com.example.mynewsapp.repository.sourcesRepository

import com.example.mynewsapp.data.api.model.sourcesResponse.Source
import com.example.mynewsapp.ui.home.category.Category

interface SourcesRepository {
    suspend fun getSources(category: Category): List<Source?>?
}