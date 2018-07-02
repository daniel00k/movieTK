package me.danielaguilar.movietk.data

import me.danielaguilar.movietk.dagger.DaggerNetworkComponent
import me.danielaguilar.movietk.dagger.NetworkModule
import javax.inject.Inject

/**
 * Created by danielaguilar on 26-04-18.
 */
class RetrofitClient {

    @Inject lateinit var apiService: ApiService

    fun getRetrofit(): ApiService{
        DaggerNetworkComponent.builder()
                .networkModule(NetworkModule())
                .build()
                .inject(this)
        return apiService

    }

}