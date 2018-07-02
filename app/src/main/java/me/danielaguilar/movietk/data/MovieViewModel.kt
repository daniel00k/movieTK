package me.danielaguilar.movietk.data

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import io.reactivex.Completable
import io.reactivex.Flowable


class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository: MovieRepository = MovieRepository(application)

    val allMovies: LiveData<List<Movie>>

    init {
        allMovies = mRepository.allMovies

    }

    fun selectedMovies(name: String) : Flowable<List<Movie>> = mRepository.selectedMovies(name)

    fun insert(movie: Movie):Completable {
        return mRepository.insert(movie)
    }

    fun searchNewMovie(name: String, callbacks: MovieServiceCallbacks){
        mRepository.searchNewMovie(name, callbacks)
    }

}