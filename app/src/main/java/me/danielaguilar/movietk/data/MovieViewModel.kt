package me.danielaguilar.movietk.data

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import io.reactivex.Completable
import io.reactivex.Flowable
import me.danielaguilar.movietk.dagger.DaggerViewModelComponent
import me.danielaguilar.movietk.dagger.ViewModelModule
import javax.inject.Inject


class MovieViewModel(application: Application) : AndroidViewModel(application) {

    @Inject lateinit var mRepository: MovieRepository

    val allMovies: LiveData<List<Movie>>

    init {
        DaggerViewModelComponent.builder().viewModelModule(ViewModelModule(application)).build().inject(this)
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