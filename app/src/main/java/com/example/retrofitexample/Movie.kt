package com.example.retrofitexample

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie(
    @Json(name = "imdb_id")
    val id: String,
    val title: String,
    val rating: MovieRating,
    val year: String

)