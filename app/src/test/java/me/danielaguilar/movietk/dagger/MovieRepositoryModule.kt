package me.danielaguilar.movietk.dagger

import android.arch.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import me.danielaguilar.movietk.data.Movie
import me.danielaguilar.movietk.data.MovieDao
import me.danielaguilar.movietk.data.MovieTKRoomDatabase
import org.mockito.Mockito
import org.mockito.Mockito.mock

/**
 * Created by danielaguilar on 16-07-18.
 */
@Module
class MovieRepositoryModule(val db: MovieTKRoomDatabase){

    @Provides
    fun provideMovieDao(): MovieDao {
        val movieDao = mock(MovieDao::class.java)
        Mockito.`when`(movieDao.findAll()).thenReturn(MutableLiveData<List<Movie>>())
        return movieDao
    }
}