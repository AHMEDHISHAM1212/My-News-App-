package com.example.mynewsapp.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.mynewsapp.R
import com.example.mynewsapp.databinding.ActivitySplashBinding
import com.example.mynewsapp.ui.home.HomeActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        navigateToHome()
    }

    private fun navigateToHome() {
        Handler(Looper.getMainLooper())
            .postDelayed({
                startActivity(Intent(this,HomeActivity::class.java))
            },2000)
    }
}