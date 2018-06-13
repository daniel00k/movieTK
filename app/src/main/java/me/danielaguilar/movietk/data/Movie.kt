package me.danielaguilar.movietk.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity(tableName = "movies")
data class Movie(@PrimaryKey(autoGenerate = true) @NonNull val id: Long, @NonNull val name:String)