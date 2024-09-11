package com.example.mynewsapp.ui.home.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynewsapp.data.api.ApiManager
import com.example.mynewsapp.data.api.model.newsResponse.NewsItem
import com.example.mynewsapp.data.api.model.newsResponse.NewsResponse
import com.example.mynewsapp.data.api.model.sourcesResponse.Source
import com.example.mynewsapp.data.api.model.sourcesResponse.SourcesResponse
import com.example.mynewsapp.data.dataSourceImpl.newsDataSourceImpl.NewsOnlineDataSourceImpl
import com.example.mynewsapp.data.dataSourceImpl.sourcesDataSourceImpl.SourcesOnlineDataSourceImpl
import com.example.mynewsapp.data.repositoryImpl.newsRepositoryImpl.NewsRepositoryImpl
import com.example.mynewsapp.data.repositoryImpl.sourcesRepositoryImpl.SourcesRepositoryImpl
import com.example.mynewsapp.dataSource.NewsDataSource.NewsDataSource
import com.example.mynewsapp.dataSource.sourcesDataSource.SourcesDataSource
import com.example.mynewsapp.repository.newsRepository.NewSourcesRepository
import com.example.mynewsapp.repository.sourcesRepository.SourcesRepository
import com.example.mynewsapp.ui.ViewError
import com.example.mynewsapp.ui.home.category.Category
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class NewsViewModel: ViewModel() {
    val shouldShowLoading = MutableLiveData<Boolean>()
    val sourcesLiveData = MutableLiveData<List<Source?>?>()
    val newsLiveData = MutableLiveData<List<NewsItem?>?>()
    val errorLiveData = MutableLiveData<ViewError>()

    private val sourcesOnlineDataSourceImpl: SourcesDataSource = SourcesOnlineDataSourceImpl(
        ApiManager.getApis()
    )
    private val sourcesRepo: SourcesRepository = SourcesRepositoryImpl(sourcesOnlineDataSourceImpl)

    private val newsOnlineDataSource: NewsDataSource = NewsOnlineDataSourceImpl(
        ApiManager.getApis()
    )
    private val newsRep: NewSourcesRepository = NewsRepositoryImpl(newsOnlineDataSource)

    fun getNewsSources(category: Category) {
        viewModelScope.launch {
            shouldShowLoading.postValue(true)
            try {
               val sources = sourcesRepo.getSources(category)
                sourcesLiveData.postValue(sources)
            }catch (e: HttpException){
                // means the error from the server
                val errorBodyJsonString =e.response()?.errorBody()?.string()
                        val errorResponse = Gson().fromJson(errorBodyJsonString, SourcesResponse::class.java)
                        errorLiveData.postValue(
                            ViewError(
                                message = errorResponse.message
                            ){
                                getNewsSources(category)
                            })
            }catch (e: Exception){
                // general exception
                shouldShowLoading.postValue(false)
                    errorLiveData.postValue(
                        ViewError(
                            throwable = e
                        ){
                            getNewsSources(category = category)
                        })
            }finally {
                shouldShowLoading.postValue(false)
            }
        }
    }
    fun getNews(sourceId: String?) {
        viewModelScope.launch {
            shouldShowLoading.postValue(true)
            try {
                val articles = newsRep.getNews(sourceId!!)
                newsLiveData.postValue(articles)
            }catch (e: HttpException){
                val responseJsonError = e.response()?.errorBody()?.string()
                val errorResponse = Gson().fromJson(responseJsonError, NewsResponse::class.java)
                errorLiveData.postValue(
                    ViewError(
                        message = errorResponse.message
                    ){
                        getNews(sourceId)
                    }
                )
            }
            catch (e: Exception){
                errorLiveData.postValue(
                    ViewError(
                        throwable = e
                    ){
                        getNews(sourceId)
                    }
                )
            }finally {
                shouldShowLoading.postValue(false)
            }
        }
    }
}