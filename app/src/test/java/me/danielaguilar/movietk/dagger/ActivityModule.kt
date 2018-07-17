package me.danielaguilar.movietk.dagger

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import me.danielaguilar.movietk.data.MovieViewModel
import me.danielaguilar.movietk.movie_list.MovieListActivity
import me.danielaguilar.movietk.movie_list.MovieListAdapter

/**
 * Created by danielaguilar on 15-07-18.
 */
@Module
class ActivityModule(private val activity: AppCompatActivity){
    @Provides
    fun provideViewModel(): MovieViewModel {
        return ViewModelProviders.of(activity).get(MovieViewModel::class.java)
    }

    @Provides
    fun provideMovieListAdapter():MovieListAdapter{
        return MovieListAdapter(activity as MovieListActivity)
    }
}