package com.example.mynewsapp.ui


data class ViewError(
    val message: String?= null,
    val throwable: Throwable?= null,
    val onTryAgainClickListener: OnTryAgainClickListener?=null
)
fun interface OnTryAgainClickListener{
    fun onTryAgainCLick()
}