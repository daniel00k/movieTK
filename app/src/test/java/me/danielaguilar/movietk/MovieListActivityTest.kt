package me.danielaguilar.movietk

import android.content.Intent
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.RecyclerView
import me.danielaguilar.movietk.add_movie.AddMovieActivity
import me.danielaguilar.movietk.movie_list.MovieListActivity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf


@RunWith(RobolectricTestRunner::class)
class MovieListActivityTest{

    private lateinit var movieListActivity: MovieListActivity

    @Before
    fun setup(){
        movieListActivity = Robolectric.setupActivity(MovieListActivity::class.java)
    }

    @Test
    fun clickFloatButton_shouldStartAddMovieActivity(){

        val button = movieListActivity.findViewById(R.id.addMovie) as FloatingActionButton
        button.performClick()
        val expectedIntent = Intent(movieListActivity, AddMovieActivity::class.java)
        val actual = shadowOf(RuntimeEnvironment.application).nextStartedActivity
        assertEquals(expectedIntent.component, actual.component)
    }

    @Test
    fun onActivityLoad_shouldBeEmpty(){

        val recycler = movieListActivity.findViewById(R.id.recyclerView) as RecyclerView
        assertEquals(recycler.adapter.itemCount, 0)
    }

}