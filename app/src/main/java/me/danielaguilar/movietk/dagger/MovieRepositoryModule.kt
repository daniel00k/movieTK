package me.danielaguilar.movietk.dagger

import dagger.Module
import dagger.Provides
import me.danielaguilar.movietk.data.MovieDao
import me.danielaguilar.movietk.data.MovieTKRoomDatabase

/**
 * Created by danielaguilar on 16-07-18.
 */
@Module
class MovieRepositoryModule(val db: MovieTKRoomDatabase){
    @Provides
    fun provideMovieDao(): MovieDao {
        return db.movieDao()
    }
}