package com.example.mynewsapp.ui

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
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

@BindingAdapter("onClickViews")
fun onClickListenerViews(view: View?
                         ,url : String? ) {
    view?.setOnClickListener {
        it
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        view.context.startActivity(intent)
    }
}

@BindingAdapter("imageId")
fun loadDrawableImageById(
    imageView: ImageView,
    imageId: Int
){
    imageView.setImageResource(imageId)
}

@BindingAdapter("backgroundColor")
fun changeCardViewBackground(
    cardView: CardView,
    color: Int
){
    cardView.setCardBackgroundColor(ContextCompat.getColor(cardView.context,color))
}