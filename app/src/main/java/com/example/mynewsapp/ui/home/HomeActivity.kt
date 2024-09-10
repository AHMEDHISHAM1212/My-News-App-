package com.example.mynewsapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mynewsapp.ui.home.news.NewsFragment
import com.example.mynewsapp.R
import com.example.mynewsapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        pushFragment()
    }

    private fun pushFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, NewsFragment())
            .commit()
    }


}