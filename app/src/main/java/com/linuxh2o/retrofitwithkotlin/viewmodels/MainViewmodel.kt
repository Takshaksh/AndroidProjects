package com.linuxh2o.retrofitwithkotlin.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linuxh2o.retrofitwithkotlin.api.RetrofitInstance
import kotlinx.coroutines.launch

private const val TAG = "MainViewModel"
class MainViewmodel: ViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading : LiveData<Boolean>
        get() = _isLoading

    fun getPosts() {
        viewModelScope.launch {
            val fetchedPosts = RetrofitInstance.api.getPosts()
            Log.i(TAG, "getPosts: $${fetchedPosts}")
        }
    }
}