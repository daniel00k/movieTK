package me.danielaguilar.movietk.root

import android.app.Application
import me.danielaguilar.movietk.dagger.*

class MovieTKApplication : Application() {
    lateinit var component: AppComponent
    companion object {
        lateinit var netWorkComponent: NetworkComponent
    }


    override fun onCreate() {
        super.onCreate()
        initDagger(this)
    }

    private fun initDagger(context: Application){
        component = DaggerAppComponent.builder()
                .appModule(AppModule(context))
                .build()
        netWorkComponent = DaggerNetworkComponent.builder()
                        .networkModule(NetworkModule())
                        .build()


    }
}