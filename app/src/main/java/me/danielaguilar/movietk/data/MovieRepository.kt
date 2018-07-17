package me.danielaguilar.movietk.data

import android.arch.lifecycle.LiveData
import android.content.Context
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.danielaguilar.movietk.dagger.DaggerMovieRepositoryComponent
import me.danielaguilar.movietk.dagger.MovieRepositoryModule
import javax.inject.Inject


class MovieRepository(context: Context) {

    @Inject lateinit var movieDao: MovieDao
    val allMovies: LiveData<List<Movie>>

    init {
        val db = FactoryDAO.getDatabase(context)
        DaggerMovieRepositoryComponent.builder()
                .movieRepositoryModule(MovieRepositoryModule(db))
                .build()
                .inject(this)
        allMovies = /*MutableLiveData<List<Movie>>()*/ movieDao.findAll()
    }

    fun selectedMovies(name: String) : Flowable<List<Movie>> = movieDao.findAllByName(name).subscribeOn(Schedulers.io())

    fun insert(movie: Movie): Completable {

        return Completable.fromAction { movieDao.insert(movie) }
                .subscribeOn(Schedulers.io())

    }

    fun searchNewMovie(name: String, callbacks: MovieServiceCallbacks){
        getRetrofit().searchMovieByName(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    callbacks.onMovieSearchFinish(it.results)
                },{
                    println(it.message)
                })

    }

    private fun getRetrofit(): ApiService{
        return RetrofitClient().getRetrofit()
    }
}