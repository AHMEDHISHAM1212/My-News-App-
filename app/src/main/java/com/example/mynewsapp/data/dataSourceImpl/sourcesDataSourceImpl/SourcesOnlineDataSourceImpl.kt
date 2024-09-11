package com.example.mynewsapp.data.dataSourceImpl.sourcesDataSourceImpl

import com.example.mynewsapp.data.api.WebServices
import com.example.mynewsapp.data.api.model.sourcesResponse.Source
import com.example.mynewsapp.dataSource.sourcesDataSource.SourcesDataSource
import com.example.mynewsapp.ui.home.category.Category

class SourcesOnlineDataSourceImpl (private val webServices: WebServices): SourcesDataSource {
    override suspend fun getSources(category: Category): List<Source?>? {
        val response = webServices.getSources(category = category.id)
        return response.sources
    }

}