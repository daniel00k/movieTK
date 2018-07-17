package me.danielaguilar.movietk.dagger

import dagger.Component
import me.danielaguilar.movietk.data.MovieViewModel

/**
 * Created by danielaguilar on 16-07-18.
 */
@Component(modules = [ViewModelModule::class])
interface ViewModelComponent {
    fun inject(target: MovieViewModel)
}