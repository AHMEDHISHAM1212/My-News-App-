package com.example.mynewsapp.ui.home.news

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.mynewsapp.api.ApiManager
import com.example.mynewsapp.api.model.newsResponse.NewsResponse
import com.example.mynewsapp.api.model.sourcesResponse.Source
import com.example.mynewsapp.api.model.sourcesResponse.SourcesResponse
import com.example.mynewsapp.databinding.FragmentNewsBinding
import com.example.mynewsapp.ui.showDialog
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment: Fragment() {
    private lateinit var viewBinding: FragmentNewsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentNewsBinding
            .inflate(layoutInflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getNewsSources()
        initRecyclerView()
    }

    private val newsAdapter = NewsAdapter(null)
    private fun initRecyclerView() {
        viewBinding.rvNews.adapter = newsAdapter
    }

    private fun getNewsSources() {
        viewBinding.progressBar.isVisible = true
//        viewBinding.progressBar.visibility = View.VISIBLE
        ApiManager.getApis()
            .getSources()
            .enqueue(object: Callback<SourcesResponse>{
                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    Log.i("NewsFragment","onFailure")
                    viewBinding.progressBar.isVisible = false
                    handelError(t) {
                        getNewsSources()
                    }

                }
                override fun onResponse(call: Call<SourcesResponse>, response: Response<SourcesResponse>) {
                    viewBinding.progressBar.isVisible = false
                    if (response.isSuccessful){
                        Log.i("NewsFragment","onResponse & success")
                        //show tabs in fragment.
                        bindTabs(response.body()?.sources)
                    }else{
                        Log.i("NewsFragment","onResponse & fail")
                        // error from server and get the body error and
                        // convert it into response and get the message of the error.
                        val errorBodyJsonString =response.errorBody()?.string()
                        val errorResponse = Gson().fromJson(errorBodyJsonString, SourcesResponse::class.java)
                        handelError(errorResponse.message){
                            getNewsSources()
                        }
                    }
                }
            })
    }
    private fun bindTabs(sources: List<Source?>?) {
        if (sources==null)
            //TODO handel if not found sources to show
            return
        sources.forEach {source ->
            val tab = viewBinding.tabLayout.newTab()
            tab.text = source?.name
            tab.tag = source
            viewBinding.tabLayout.addTab(tab)
        }
        initTabsClicks()
    }

    private fun initTabsClicks() {
        viewBinding.tabLayout.addOnTabSelectedListener(
            object: TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val source = tab?.tag as Source
                    getNews(source.id)
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    val source = tab?.tag as Source
                    getNews(source.id)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }
            }
        )
        viewBinding.tabLayout.getTabAt(0)?.select()
    }

    private fun getNews(sourceId: String?) {
        viewBinding.progressBar.isVisible = true
        ApiManager.getApis()
            .getNews(sources = sourceId!!)
            .enqueue(object :Callback<NewsResponse> {
                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    viewBinding.progressBar.isVisible = false
                    handelError(t){
                        getNews(sourceId)
                    }
                }

                override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                    viewBinding.progressBar.isVisible = false
                    if (response.isSuccessful){
                        //show news
                        newsAdapter.bindNews(response.body()?.articles)
                        return
                    }
                    val responseJsonError = response.errorBody()?.string()
                    val errorResponse = Gson().fromJson(responseJsonError,NewsResponse::class.java)
                    handelError(errorResponse.message){
                        getNews(sourceId)
                    }

                }

            })
    }
    fun interface OnTryAgainClickListener{
        fun onTryAgainCLick()
    }
    fun handelError(t: Throwable , onClick: OnTryAgainClickListener){
        showDialog(t.localizedMessage?:"Something went wrong",
            posActionName = "Try again!",
            posAction = {dialog: DialogInterface, i: Int ->
                dialog.dismiss()
                onClick.onTryAgainCLick() },
            negActionName = "Cancel",
            negAction = {dialog, i ->
                dialog.dismiss()
            }
        )
    }
    fun handelError(message:String?,onClick: OnTryAgainClickListener){
        showDialog(message?:"Something went wrong",
            posActionName = "Try again!",
            posAction = {dialog: DialogInterface, i: Int ->
                dialog.dismiss()
                onClick.onTryAgainCLick()},
            negActionName = "Cancel",
            negAction = {dialog, i ->
                dialog.dismiss()
            }
        )
    }
}