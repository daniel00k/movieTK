package me.danielaguilar.movietk.root

import android.app.Application
import me.danielaguilar.movietk.dagger.AppComponent
import me.danielaguilar.movietk.dagger.AppModule
import me.danielaguilar.movietk.dagger.DaggerAppComponent

class MovieTKApplication : Application() {
    lateinit var component: AppComponent
    override fun onCreate() {
        super.onCreate()
        initDagger(this)
    }

    private fun initDagger(context: Application){
        component = DaggerAppComponent.builder()
                .appModule(AppModule(context))
                .build()
    }
}