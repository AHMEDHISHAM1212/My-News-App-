package com.example.mynewsapp.ui.home.category

import com.example.mynewsapp.R

data class Category(
    var id: String,
    var name: String,
    var imageId: Int,
    var backgroundColorId: Int
){
    companion object{
        fun getCategoryList(): List<Category>{
            return listOf(
                Category("sports",
                    "Sports",
                    R.drawable.ic_sprots,
                    R.color.gold
                ),
                Category("entertainment",
                    "Entertainment",
                    R.drawable.ic_entertainment,
                    R.color.blue
                ),
                Category("health",
                    "General health",
                    R.drawable.ic_health,
                    R.color.bink
                ),
                Category("business",
                    "Business",
                    R.drawable.ic_business,
                    R.color.yellow
                ),
                Category("technology",
                    "Technology",
                    R.drawable.ic_tech,
                    R.color.red
                ),
                Category("science",
                    "Science",
                    R.drawable.ic_sicence,
                    R.color.light_blue
                ),
            )
        }
    }
}
