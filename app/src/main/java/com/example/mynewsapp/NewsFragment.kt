package com.example.mynewsapp

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.mynewsapp.api.ApiManager
import com.example.mynewsapp.api.model.SourcesResponse
import com.example.mynewsapp.databinding.FragmentNewsBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment: Fragment() {
    private lateinit var viewBinding: FragmentNewsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentNewsBinding
            .inflate(layoutInflater,container,false)
        return viewBinding.root
        getNewsSources()
    }

    private fun getNewsSources() {
        viewBinding.progressBar.isVisible = true
//        viewBinding.progressBar.visibility = View.VISIBLE
        ApiManager.getApis()
            .getSources()
            .enqueue(object: Callback<SourcesResponse>{
                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    viewBinding.progressBar.isVisible = false
                    showDialog(t.localizedMessage?:"Something went wrong",
                        posActionName = "Try again!",
                        posAction = {dialog: DialogInterface, i: Int ->
                            dialog.dismiss()
                            getNewsSources() },
                        negActionName = "Cancel",
                        negAction = {dialog, i ->
                            dialog.dismiss()
                        }
                    )
                }

                override fun onResponse(call: Call<SourcesResponse>, response: Response<SourcesResponse>) {
                    if (response.isSuccessful){
                        viewBinding.progressBar.isVisible = false
                        //show tabs in fragment.
                    }else{
                        viewBinding.progressBar.isVisible = false
                        // error from server and get the body error and
                        // convert it into response and get the message of the error.
                        val errorBodyJsonString =response.errorBody()?.string()
                        val response = Gson().fromJson(errorBodyJsonString,SourcesResponse::class.java)
                        showDialog(response.message?:"Something went wrong",
                            posActionName = "Try again!",
                            posAction = {dialog: DialogInterface, i: Int ->
                                dialog.dismiss()
                                getNewsSources() },
                            negActionName = "Cancel",
                            negAction = {dialog, i ->
                                dialog.dismiss()
                            }
                        )
                    }
                }
            })

    }
}