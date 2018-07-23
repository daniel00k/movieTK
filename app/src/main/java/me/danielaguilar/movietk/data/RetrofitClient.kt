package me.danielaguilar.movietk.data

import me.danielaguilar.movietk.root.MovieTKApplication
import javax.inject.Inject

/**
 * Created by danielaguilar on 26-04-18.
 */
class RetrofitClient {

    @Inject lateinit var apiService: ApiService

    fun getRetrofit(): ApiService{
        MovieTKApplication.netWorkComponent.inject(this)
        return apiService

    }

}