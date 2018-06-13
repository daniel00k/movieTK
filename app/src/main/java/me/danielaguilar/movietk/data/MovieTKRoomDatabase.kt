package me.danielaguilar.movietk.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase


@Database(entities = [(Movie::class)], version = 1, exportSchema = false)
abstract class MovieTKRoomDatabase : RoomDatabase(){

    abstract fun movieDao(): MovieDao

}