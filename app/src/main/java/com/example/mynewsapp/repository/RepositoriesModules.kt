package com.example.mynewsapp.repository

import com.example.mynewsapp.data.dataSourceImpl.newsDataSourceImpl.NewsOnlineDataSourceImpl
import com.example.mynewsapp.data.dataSourceImpl.sourcesDataSourceImpl.SourcesOnlineDataSourceImpl
import com.example.mynewsapp.data.repositoryImpl.newsRepositoryImpl.NewsRepositoryImpl
import com.example.mynewsapp.data.repositoryImpl.sourcesRepositoryImpl.SourcesRepositoryImpl
import com.example.mynewsapp.dataSource.NewsDataSource.NewsDataSource
import com.example.mynewsapp.dataSource.sourcesDataSource.SourcesDataSource
import com.example.mynewsapp.repository.newsRepository.NewRepository
import com.example.mynewsapp.repository.sourcesRepository.SourcesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoriesModules {

    @Binds
    abstract fun provideSourcesRepository(
        repo: SourcesRepositoryImpl
    ): SourcesRepository

    @Binds
    abstract fun provideSourcesDataSource(
        sourcesOnlineDataSource: SourcesOnlineDataSourceImpl
    ): SourcesDataSource

    @Binds
    abstract fun provideNewsRepository(
        repo: NewsRepositoryImpl
    ): NewRepository

    @Binds
    abstract fun provideNewsDataSource(
        newsOnlineDataSource: NewsOnlineDataSourceImpl
    ): NewsDataSource
}