package com.example.moviedbapi.repository

import android.util.Log
import com.example.moviedbapi.data.Movie
import com.example.moviedbapi.response.GetMoviesResponse
import com.example.moviedbapi.request.MovieApi
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

object MoviesRepository
{
    private val movieapi: MovieApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        movieapi = retrofit.create(MovieApi::class.java)
    }

    fun getPopularMovie(page: Int = 1,
                        onSuccess: (movies: List<Movie>) -> Unit,
                        onError: () -> Unit )
    {
        movieapi.getPopularMovies(page = page)
            .enqueue(object :Callback<GetMoviesResponse>{
                override fun onResponse(
                    call: Call<GetMoviesResponse>,
                    response: Response<GetMoviesResponse>
                ) {
                    if (response.isSuccessful)
                    {
                        val responseBody = response.body()

                        if (responseBody != null)
                        {
                            onSuccess.invoke(responseBody.movies)
                        }
                        else
                        {
                            onError.invoke()
                        }
                    }else
                    {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                    Log.d("Repository", "onFailure", t)
                }
            })
    }

}