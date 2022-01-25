package com.example.moviedbapi.data

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    val id: Long,

    @SerializedName("title")
    val title: String,

    @SerializedName("poster_path")
    val posterPath: String,
)
