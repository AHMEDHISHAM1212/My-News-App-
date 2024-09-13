package com.example.mynewsapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.example.mynewsapp.ui.home.news.NewsFragment
import com.example.mynewsapp.R
import com.example.mynewsapp.databinding.ActivityHomeBinding
import com.example.mynewsapp.ui.home.category.Category
import com.example.mynewsapp.ui.home.category.CategoryFragment
import com.example.mynewsapp.ui.home.settings.SettingsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityHomeBinding
    private var categoryFragment = CategoryFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        pushFragment(CategoryFragment())
        listenOnCategoryClick()
        showNavDrawer()

    }

    private fun showNavDrawer() {
        var toggle = ActionBarDrawerToggle(this,viewBinding.drawerLayout,
            viewBinding.homeToolbar,R.string.open,R.string.close)

        viewBinding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        initNavMenuClicks()
    }

    private fun initNavMenuClicks() {
        viewBinding.navHome.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_category -> pushFragment(categoryFragment)
                R.id.nav_settings -> pushFragment(SettingsFragment())
            }
            viewBinding.drawerLayout.closeDrawers()
            return@setNavigationItemSelectedListener true
        }
    }



    private fun listenOnCategoryClick() {
        categoryFragment.onCategoryItemClickListener = CategoryFragment.OnCategoryItemClickListener {
            pushFragment(it)
        }
    }

    private fun pushFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }


    private fun pushFragment(category: Category){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,NewsFragment.getInstance(category))
            .addToBackStack(null )
            .commit()
    }

}