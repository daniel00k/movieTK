package me.danielaguilar.movietk.add_movie

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_add_movie.*
import me.danielaguilar.movietk.R
import me.danielaguilar.movietk.camera.DeviceCamera
import me.danielaguilar.movietk.config.IMAGES_BASE_URL
import me.danielaguilar.movietk.data.DBMovie
import me.danielaguilar.movietk.data.Movie
import me.danielaguilar.movietk.data.MovieServiceCallbacks
import me.danielaguilar.movietk.data.MovieViewModel
import me.danielaguilar.movietk.movie_list.DBMovieListAdapter


class AddMovieActivity : AppCompatActivity(), DBMovieListAdapter.DBMovieItemListener {
    override fun onDBMovieSelected(movie: DBMovie) {
        movieDescription.setText(movie.overview, TextView.BufferType.EDITABLE)
        movieName.setText(movie.title, TextView.BufferType.EDITABLE)
        Glide.with(this).load(IMAGES_BASE_URL +movie.posterPath).into(moviePosterUrl)
        Glide.with(this).load(IMAGES_BASE_URL +movie.backdropPath).into(coverPicture)
        hideList()
    }

    companion object {
        const val REQUEST_CAPTURE_IMAGE: Int = 1
    }

    private lateinit var imageFilePath: String
    private lateinit var viewModel: MovieViewModel
    private val adapter = DBMovieListAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movie)
        viewModel   = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        movieSearch.queryHint = "buscar"

        movieSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                recyclerView.visibility= View.GONE
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.searchNewMovie(newText, object : MovieServiceCallbacks{

                    override fun onMovieSearchFinish(movies: List<DBMovie>) {
                        adapter.updateMovies(movies)
                    }

                })
                if(TextUtils.isEmpty(newText)){
                    hideList()
                }else{
                    showList()
                }

                return false
            }
        })

        movieSearch.setOnCloseListener {
            recyclerView.visibility= View.GONE
            false
        }
        clickMe.setOnClickListener {
            DeviceCamera.openCameraIntent(this, REQUEST_CAPTURE_IMAGE) {
                imageFilePath = it
            }
        }
        saveMovie.setOnClickListener {

            val movie  = Movie(movieSearch.query.toString(),
                    movieDescription.text.toString(),
                    "https://cdn.shopify.com/s/files/1/0151/0741/products/pg1012_1024x1024.jpg",
                    "http://as01.epimg.net/betech/imagenes/2016/08/16/portada/1471354374_257181_1471354514_noticia_normal.jpg")

            viewModel.insert(movie).subscribe(
                        {
                            finish()
                        },
                        {
                            Log.d("TAG", it.message)
                        })
        }

        movieSearch.isIconified=false

    }

    private fun showList(){
        recyclerView.visibility     =   View.VISIBLE
        formContainer.visibility    =   View.GONE
    }

    private fun hideList(){
        recyclerView.visibility     =   View.GONE
        formContainer.visibility    =   View.VISIBLE
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int,
                                  data: Intent?) {
        if (requestCode == REQUEST_CAPTURE_IMAGE) {
            Glide.with(this).load(imageFilePath).into(moviePictureUrl)
        }else if(resultCode == Activity.RESULT_CANCELED) {
            // User Cancelled the action
        }
    }

}
