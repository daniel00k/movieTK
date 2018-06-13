package me.danielaguilar.movietk.data

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData


class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository: MovieRepository = MovieRepository(application)

    val allMovies: LiveData<List<Movie>>

    init {
        allMovies = mRepository.allMovies
    }

    fun insert(movie: Movie) {
        mRepository.insert(movie)
    }
}