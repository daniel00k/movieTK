package me.danielaguilar.movietk.data
import com.google.gson.annotations.SerializedName


data class DBMovie (@SerializedName("vote_count") val voteCount: Int?,
                    val id: Int?,
                    val video: Boolean?,
                    @SerializedName("vote_average") val voteAverage: Float?,
                    val title: String?,
                    val popularity: Double?,
                    @SerializedName("poster_path") val posterPath: String?,
                    @SerializedName("original_language")
                    val originalLanguage: String?,
                    @SerializedName("original_title") val originalTitle: String?,
                    @SerializedName("genre_ids") val genreIds: List<Int>?,
                    @SerializedName("backdrop_path")
                    val backdropPath: String?,
                    val adult: Boolean?,
                    val overview: String?,
                    @SerializedName("release_date") val releaseDate: String?)