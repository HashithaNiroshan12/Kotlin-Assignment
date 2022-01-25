package com.example.moviedbapi

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedbapi.adapter.MoviesAdapter
import com.example.moviedbapi.data.Movie
import com.example.moviedbapi.repository.MoviesRepository
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.ActionBar


class MainActivity : AppCompatActivity() {

    private lateinit var popularMovies: RecyclerView
    private lateinit var popularMoviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        popularMovies = findViewById(R.id.popular_movies)
        popularMovies.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
        )

        popularMoviesAdapter = MoviesAdapter(listOf())
        popularMovies.adapter = popularMoviesAdapter

        MoviesRepository.getPopularMovie(
            onSuccess = ::onPopularMoviesFetched,
            onError = ::onError
        )
    }

    private fun onPopularMoviesFetched(movies:List<Movie>)
    {
        popularMoviesAdapter.updateMovies(movies)
    }

    private fun onError()
    {
        Toast.makeText(this, getString(R.string.error_fetch_movies),
            Toast.LENGTH_SHORT).show()
    }
}


