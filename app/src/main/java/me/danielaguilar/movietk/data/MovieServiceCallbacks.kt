package me.danielaguilar.movietk.data

interface MovieServiceCallbacks{
    fun onMovieSearchFinish(movies: List<DBMovie>)
}