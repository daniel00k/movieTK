package me.danielaguilar.movietk.data

import android.arch.lifecycle.LiveData
import android.content.Context
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers


class MovieRepository(context: Context) {

    private val movieDao: MovieDao
    val allMovies: LiveData<List<Movie>>

    init {
        val db = FactoryDAO.getDatabase(context)
        movieDao = db.movieDao()
        allMovies = movieDao.findAll()
    }


    fun insert(movie: Movie): Completable {

        return Completable.fromAction { movieDao.insert(movie) }
                .subscribeOn(Schedulers.io())


    }
}