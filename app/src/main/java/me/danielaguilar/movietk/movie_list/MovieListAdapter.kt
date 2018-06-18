package me.danielaguilar.movietk.movie_list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import me.danielaguilar.movietk.R
import me.danielaguilar.movietk.data.Movie

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {
    var movies: List<Movie> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(position)
    }

    fun updateMovies(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(position: Int) {
            val movieTitle: TextView = view.findViewById(R.id.movie_title)
            movieTitle.text = "id: ${movies[position].id},name: ${movies[position].name}"
        }

    }
}