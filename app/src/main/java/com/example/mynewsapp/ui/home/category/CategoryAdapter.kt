package com.example.mynewsapp.ui.home.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynewsapp.databinding.ItemCategoryBinding

class CategoryAdapter(var categoryList: List<Category>): RecyclerView.Adapter<CategoryAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var itemCategoryBinding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(itemCategoryBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = categoryList[position]
        holder.bind(item)

        if (onItemClickListener!=null){
            holder.itemView.setOnClickListener{
                onItemClickListener?.onItemClick(item,position)
            }
        }

    }

    override fun getItemCount(): Int = categoryList.size

    var onItemClickListener: OnItemClickListener?= null
    fun interface OnItemClickListener{
        fun onItemClick(category: Category,position: Int)
    }
    class ViewHolder(val itemCategoryBinding: ItemCategoryBinding):
        RecyclerView.ViewHolder(itemCategoryBinding.root){
            fun bind(category: Category){
                itemCategoryBinding.category = category
                itemCategoryBinding.executePendingBindings()

            }
        }
}