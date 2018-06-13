package me.danielaguilar.movietk.data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: Movie): Long

    @Update
    fun update(movie: Movie)

    @Query("SELECT * FROM movies")
    fun findAll(): LiveData<List<Movie>>

    @Query("SELECT * FROM movies WHERE movies.id= :id LIMIT 1")
    fun findById(id: Long): Movie

    @Delete
    fun deleteMovies(vararg movies: Movie)

    @Query("DELETE FROM movies")
    fun deleteAll()

}