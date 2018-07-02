package me.danielaguilar.movietk.dagger

import dagger.Component
import me.danielaguilar.movietk.data.RetrofitClient
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface NetworkComponent {
    fun inject(target: RetrofitClient)
}