package com.example.mvi_design_patern.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.mvi_design_patern.R
import com.example.mvi_design_patern.model.Blog
import com.example.mvi_design_patern.util.DataState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

//    val textView = findViewById<TextView>(R.id.text_view_id)
//    val progressBar = findViewById<ProgressBar>(R.id.progress_bar)

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvent)
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<Blog>> -> {
                    displayProgressBar(false)
                    appendBlogTitles(dataState.data)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    @SuppressLint("ResourceType")
    private fun displayError(message: String?) {
        if (message != null) {
//            textView.text = message
        } else {
//            textView.text = "Unknown error"
        }
    }

    private fun appendBlogTitles(blogs: List<Blog>) {
        val sb = StringBuilder()
        for (blog in blogs) {
            sb.append(blog.title + "\n")
        }
//        textView.text = sb.toString()
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
//        progressBar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }
}