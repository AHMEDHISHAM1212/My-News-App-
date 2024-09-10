package com.example.mynewsapp.ui.home.news

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mynewsapp.api.model.sourcesResponse.Source
import com.example.mynewsapp.databinding.FragmentNewsBinding
import com.example.mynewsapp.ui.ViewError
import com.example.mynewsapp.ui.showDialog
import com.google.android.material.tabs.TabLayout


class NewsFragment: Fragment() {
    private lateinit var viewBinding: FragmentNewsBinding
    private lateinit var viewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
    }
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
        viewModel.getNewsSources()
        initViews()
        initObservers()
        initRecyclerView()
    }

    private fun initViews() {
        viewBinding.vm = viewModel
        viewBinding.lifecycleOwner = this
    }

    private fun initObservers() {
//        viewModel.shouldShowLoading.observe(viewLifecycleOwner) {
//            value -> viewBinding.progressBar.isVisible = value }
        viewModel.sourcesLiveData.observe(viewLifecycleOwner){sources->
            bindTabs(sources)
        }
        viewModel.newsLiveData.observe(viewLifecycleOwner){news->
            newsAdapter.bindNews(news)
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner){viewError->
            handelError(viewError)
        }
    }

    private val newsAdapter = NewsAdapter(null)
    private fun initRecyclerView() {
        viewBinding.rvNews.adapter = newsAdapter
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
                    viewModel.getNews(source.id)
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    val source = tab?.tag as Source
                    viewModel.getNews(source.id)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }
            }
        )
        viewBinding.tabLayout.getTabAt(0)?.select()
    }
    private fun handelError(viewError: ViewError){
        showDialog(viewError.message?:viewError.throwable?.localizedMessage?:"Something went wrong",
            posActionName = "Try again!",
            posAction = {dialog: DialogInterface, i: Int ->
                dialog.dismiss()
             viewError.onTryAgainClickListener?.onTryAgainCLick()},
            negActionName = "Cancel",
            negAction = {dialog, i ->
                dialog.dismiss()
            }
        )
    }
}