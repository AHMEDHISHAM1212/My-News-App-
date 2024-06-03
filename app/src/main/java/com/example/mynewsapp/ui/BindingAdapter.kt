package com.example.mynewsapp.ui

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.mynewsapp.R

@BindingAdapter(value =["app:url","app:placeHolder"])
fun bindImageWithUrl(
    imageView: ImageView,
    url: String?,
    placeHolder: Drawable?
){
    Glide.with(imageView)
        .load(url)
        .placeholder(placeHolder)
        .into(imageView)
}