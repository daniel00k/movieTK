package me.danielaguilar.movietk.dagger

import dagger.Component
import me.danielaguilar.movietk.add_movie.AddMovieActivity
import me.danielaguilar.movietk.movie_list.MovieListActivity

/**
 * Created by danielaguilar on 15-07-18.
 */
@Component(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(target: MovieListActivity)
    fun inject(target: AddMovieActivity)
}