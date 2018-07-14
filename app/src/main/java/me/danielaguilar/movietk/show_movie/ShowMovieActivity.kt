package me.danielaguilar.movietk.show_movie

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_show_movie.*
import me.danielaguilar.movietk.R
import me.danielaguilar.movietk.data.Movie

class ShowMovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_movie)
        initializeView(intent.extras.get("movie") as Movie)
    }

    private fun initializeView(movie: Movie){
        movieName.text          = movie.name
        movieDescription.text   = movie.description
        Glide.with(this).load(movie.posterUrl).into(moviePicture)
        Glide.with(this).load(movie.pictureUrl).into(coverPicture)
    }
}
