package me.danielaguilar.movietk.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import android.support.annotation.NonNull

@Entity(tableName = "movies")
class Movie(@NonNull val name:String,
            val description: String,
            @ColumnInfo(name="poster_url")      val posterUrl: String,
            @ColumnInfo(name="picture_url")     val pictureUrl: String):Parcelable {

    @PrimaryKey(autoGenerate = true) var id: Long = 0
    @ColumnInfo(name="location_url") var locationUrl: String = ""

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
        id = parcel.readLong()
        locationUrl = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(posterUrl)
        parcel.writeString(pictureUrl)
        parcel.writeLong(id)
        parcel.writeString(locationUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}

