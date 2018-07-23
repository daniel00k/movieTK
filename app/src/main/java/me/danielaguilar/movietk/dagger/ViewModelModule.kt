package me.danielaguilar.movietk.dagger

import android.app.Application
import dagger.Module
import dagger.Provides
import me.danielaguilar.movietk.data.MovieRepository

/**
 * Created by danielaguilar on 16-07-18.
 */
@Module
class ViewModelModule(val application: Application){

    @Provides
    fun provideMovieRepository(): MovieRepository{
        return MovieRepository(application)
    }
}