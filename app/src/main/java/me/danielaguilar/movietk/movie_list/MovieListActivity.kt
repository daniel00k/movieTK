package me.danielaguilar.movietk.movie_list

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_movie_list.*
import me.danielaguilar.movietk.R
import me.danielaguilar.movietk.add_movie.AddMovieActivity
import me.danielaguilar.movietk.dagger.ActivityModule
import me.danielaguilar.movietk.dagger.DaggerActivityComponent
import me.danielaguilar.movietk.data.Movie
import me.danielaguilar.movietk.data.MovieViewModel
import me.danielaguilar.movietk.show_movie.ShowMovieActivity
import javax.inject.Inject


class MovieListActivity : AppCompatActivity(), MovieListAdapter.MovieItemListener {

    private val SPAN_COUNT = 3
    @Inject lateinit var adapter:MovieListAdapter
    @Inject lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .build()
                .inject(this)
        recyclerView.layoutManager = StaggeredGridLayoutManager(SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter = adapter

        viewModel.allMovies.observe(this, Observer<List<Movie>> { movies ->
            // Update the cached copy of the movies in the adapter.
            adapter.updateMovies(movies!!)
        })

        addMovie.setOnClickListener { addMovie() }
    }

    private fun addMovie(){
        val intent = Intent(this, AddMovieActivity::class.java)
        startActivity(intent)
    }

    override fun onMovieSelected(movie: Movie) {
        val intent = Intent(this, ShowMovieActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }

}
