package com.example.mynewsapp.ui.home.news

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mynewsapp.data.api.model.newsResponse.NewsItem
import com.example.mynewsapp.databinding.ItemNewsBinding

class NewsAdapter(var newsList: List<NewsItem?>?) :RecyclerView.Adapter<NewsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = newsList!![position]
        holder.bind(news)

        if (onItemClickListener!=null){
            holder.itemNewsBinding.root
                .setOnClickListener{
                    onItemClickListener!!.onItemClick(position,news)
                }
        }
    }

    override fun getItemCount(): Int {
        return newsList?.size?:0
    }

    fun bindNews(articles: List<NewsItem?>?) {
        this.newsList =articles
        notifyDataSetChanged()
    }

    var onItemClickListener: OnItemClickListener?= null
    fun interface OnItemClickListener{
        fun onItemClick(position: Int , news: NewsItem?)
    }
    class ViewHolder(val itemNewsBinding: ItemNewsBinding): RecyclerView.ViewHolder(itemNewsBinding.root){
        fun bind(news: NewsItem?){
            itemNewsBinding.news = news
            itemNewsBinding.executePendingBindings()
        }
    }

}