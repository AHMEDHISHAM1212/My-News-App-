package com.example.mynewsapp.ui.home.category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mynewsapp.databinding.FragmentCategoryBinding

open class CategoryFragment: Fragment() {
    private lateinit var viewBinding: FragmentCategoryBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentCategoryBinding.inflate(inflater,
            container,
            false
            )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private lateinit var categoryAdapter: CategoryAdapter
    private fun initRecyclerView() {
        categoryAdapter = CategoryAdapter(Category.getCategoryList())
        viewBinding.rvCategory.adapter = categoryAdapter
        initAdapterClick()
    }

    private fun initAdapterClick() {
        categoryAdapter.onItemClickListener = 
            CategoryAdapter.OnItemClickListener{category,_ ->
                Log.d("catAdapterClick","Clicked")
                onCategoryItemClickListener?.onCategoryItemClick(category)
            }
    }

    var onCategoryItemClickListener: OnCategoryItemClickListener?= null
    fun interface OnCategoryItemClickListener{
        fun onCategoryItemClick(category: Category)
    }
}