package me.danielaguilar.movietk.movie_list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import me.danielaguilar.movietk.R
import me.danielaguilar.movietk.config.IMAGES_BASE_URL
import me.danielaguilar.movietk.data.DBMovie

class DBMovieListAdapter(val listener: DBMovieItemListener) : RecyclerView.Adapter<DBMovieListAdapter.DBMovieViewHolder>() {
    interface DBMovieItemListener {
        fun onDBMovieSelected(movie: DBMovie)
    }

    var movies: List<DBMovie> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DBMovieViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.db_movie_item, parent, false)
        return DBMovieViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: DBMovieViewHolder, position: Int) {
        holder.bind(position)
    }

    fun updateMovies(movies: List<DBMovie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    inner class DBMovieViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(position: Int) {
            val movieTitle: TextView = view.findViewById(R.id.movieTitle)
            val moviePortrait: ImageView = view.findViewById(R.id.moviePortrait)
            movieTitle.text = movies[position].title
            Glide.with(view).load(IMAGES_BASE_URL+movies[position].posterPath).into(moviePortrait)
            view.setOnClickListener {
                listener.onDBMovieSelected(movies[position])
            }
        }

    }
}