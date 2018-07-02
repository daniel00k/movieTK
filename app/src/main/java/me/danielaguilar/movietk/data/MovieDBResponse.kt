package me.danielaguilar.movietk.data
import com.google.gson.annotations.SerializedName


data class MovieDBResponse(val page: Int?,
                      @SerializedName("total_results") val  totalResults: Int?,
                      @SerializedName("total_pages") val totalPages: Int?,
                      val results: List<DBMovie>)