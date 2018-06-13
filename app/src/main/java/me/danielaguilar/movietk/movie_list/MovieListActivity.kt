package me.danielaguilar.movietk.movie_list

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_movie_list.*
import me.danielaguilar.movietk.R
import me.danielaguilar.movietk.add_movie.AddMovieActivity
import me.danielaguilar.movietk.data.Movie
import me.danielaguilar.movietk.data.MovieViewModel


class MovieListActivity : AppCompatActivity() {
    companion object {
        const val REQUEST_CODE = 1
    }
    private val adapter = MovieListAdapter()

    lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        viewModel.allMovies.observe(this, Observer<List<Movie>> { movies ->
            // Update the cached copy of the movies in the adapter.
            adapter.updateMovies(movies!!)
        })

        addMovie.setOnClickListener { addMovie() }
    }

    private fun addMovie(){
        val intent = Intent(this, AddMovieActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val movie = Movie(adapter.movies.size+1L, data!!.getStringExtra("movieName"))
            viewModel.insert(movie)
        }
    }


}
