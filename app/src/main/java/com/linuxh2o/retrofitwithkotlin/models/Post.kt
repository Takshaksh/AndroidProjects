package com.linuxh2o.retrofitwithkotlin.models

data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
    )