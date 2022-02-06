package com.linuxh2o.retrofitwithkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.linuxh2o.retrofitwithkotlin.adapters.PostAdapter
import com.linuxh2o.retrofitwithkotlin.databinding.ActivityMainBinding
import com.linuxh2o.retrofitwithkotlin.models.Post
import com.linuxh2o.retrofitwithkotlin.viewmodels.MainViewmodel

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mainViewmodel: MainViewmodel
    private val blogPosts = mutableListOf<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainViewmodel = ViewModelProvider(this)[MainViewmodel::class.java]

        mainViewmodel.isLoading.observe(this, Observer { isLoading ->
            Log.i(TAG, "isLoading: $isLoading")
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        binding.recyclerView.adapter = PostAdapter(blogPosts)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.fetchButton.setOnClickListener {
            mainViewmodel.getPosts()
        }
    }
}