package me.danielaguilar.movietk.dagger

import dagger.Component
import me.danielaguilar.movietk.data.MovieRepository

/**
 * Created by danielaguilar on 16-07-18.
 */
@Component(modules = [MovieRepositoryModule::class])
interface MovieRepositoryComponent {
    fun inject(target: MovieRepository)

}