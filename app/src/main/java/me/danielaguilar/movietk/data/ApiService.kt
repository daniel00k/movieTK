package me.danielaguilar.movietk.data

import io.reactivex.Observable
import me.danielaguilar.movietk.config.API_SEARCH_MOVIE
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(API_SEARCH_MOVIE)
    fun searchMovieByName(@Query("query") name:String): Observable<MovieDBResponse>

}