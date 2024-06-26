package com.example.mynewsapp.ui.home.news

import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mynewsapp.api.ApiManager
import com.example.mynewsapp.api.model.newsResponse.NewsItem
import com.example.mynewsapp.api.model.newsResponse.NewsResponse
import com.example.mynewsapp.api.model.sourcesResponse.Source
import com.example.mynewsapp.api.model.sourcesResponse.SourcesResponse
import com.example.mynewsapp.ui.ViewError
import com.example.mynewsapp.ui.home.category.Category
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel: ViewModel() {
    val shouldShowLoading = MutableLiveData<Boolean>()
    val sourcesLiveData = MutableLiveData<List<Source?>?>()
    val newsLiveData = MutableLiveData<List<NewsItem?>?>()
    val errorLiveData = MutableLiveData<ViewError>()
    fun getNewsSources(category: Category) {
        shouldShowLoading.postValue(true)
        ApiManager.getApis()
            .getSources(category = category.id)
            .enqueue(object: Callback<SourcesResponse> {
                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    Log.i("NewsFragment","onFailure")
                    shouldShowLoading.postValue(false)
                    errorLiveData.postValue(
                        ViewError(
                            throwable = t
                        ){
                            getNewsSources(category = category)
                        })
                }
                override fun onResponse(call: Call<SourcesResponse>, response: Response<SourcesResponse>) {
                    shouldShowLoading.postValue(false)
                    if (response.isSuccessful){
                        Log.i("NewsFragment","onResponse & success")
                        //show tabs in fragment.
                        sourcesLiveData.postValue(response.body()?.sources)
                    }else{
                        Log.i("NewsFragment","onResponse & fail")
                        // error from server and get the body error and
                        // convert it into response and get the message of the error.
                        val errorBodyJsonString =response.errorBody()?.string()
                        val errorResponse = Gson().fromJson(errorBodyJsonString, SourcesResponse::class.java)
                        errorLiveData.postValue(
                            ViewError(
                                message = errorResponse.message
                            ){
                                getNewsSources(category)
                            })
                    }
                }
            })
    }
    fun getNews(sourceId: String?) {
        shouldShowLoading.postValue(true)
        ApiManager.getApis()
            .getNews(sources = sourceId!!)
            .enqueue(object :Callback<NewsResponse> {
                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    shouldShowLoading.postValue(false)
                    errorLiveData.postValue(
                        ViewError(
                            throwable = t
                        ){
                            getNews(sourceId)
                        }
                    )
                }

                override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                    shouldShowLoading.postValue(false)
                    if (response.isSuccessful){
                        //show news
                        newsLiveData.postValue(response.body()?.articles)
                        return
                    }
                    val responseJsonError = response.errorBody()?.string()
                    val errorResponse = Gson().fromJson(responseJsonError, NewsResponse::class.java)
                    errorLiveData.postValue(
                        ViewError(
                            message = errorResponse.message
                        ){
                            getNews(sourceId)
                        }
                    )
                }
            })
    }
}