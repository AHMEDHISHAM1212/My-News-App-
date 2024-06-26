package com.example.mynewsapp.ui.home.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mynewsapp.R
import com.example.mynewsapp.api.model.newsResponse.NewsItem
import com.example.mynewsapp.databinding.ActivityNewsDetailsBinding

class NewsDetailsActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityNewsDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityNewsDetailsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initPrams()
    }

    lateinit var news: NewsItem
    private fun initPrams() {
        news = ((intent.getParcelableExtra("news")as? NewsItem)!!)
        viewBinding.news = news    }
}