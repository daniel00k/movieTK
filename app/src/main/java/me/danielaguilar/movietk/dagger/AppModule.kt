package me.danielaguilar.movietk.dagger

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val application: Application){
    @Provides
    fun provideAppContext(): Context = application
}