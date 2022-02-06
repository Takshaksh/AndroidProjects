package com.linuxh2o.retrofitwithkotlin.api

import com.linuxh2o.retrofitwithkotlin.models.Post
import retrofit2.http.GET

interface BlogAPI {

    @GET("posts")
    suspend fun getPosts() : List<Post>

}