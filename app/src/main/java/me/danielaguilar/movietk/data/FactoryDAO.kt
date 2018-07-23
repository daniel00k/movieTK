package me.danielaguilar.movietk.data

import android.arch.persistence.room.Room
import android.content.Context
import org.json.JSONObject
import org.json.JSONException



object FactoryDAO {
        var INSTANCE: MovieTKRoomDatabase? = null

        fun getDatabase(context: Context): MovieTKRoomDatabase {
            if (INSTANCE == null) {

                INSTANCE = Room.databaseBuilder(context,
                        MovieTKRoomDatabase::class.java, "movietk_database")
                        .build()

            }
            return INSTANCE!!
        }

}

