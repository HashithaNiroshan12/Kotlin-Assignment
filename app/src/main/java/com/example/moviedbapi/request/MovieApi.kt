package com.example.moviedbapi.request

import com.example.moviedbapi.response.GetMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi
{
    @GET("movie/top_rated")
    fun getPopularMovies(
        @Query("api_key")    apiKey: String = "55957fcf3ba81b137f8fc01ac5a31fb5",
        @Query("page")    page:    Int
    ):    Call<GetMoviesResponse>
}