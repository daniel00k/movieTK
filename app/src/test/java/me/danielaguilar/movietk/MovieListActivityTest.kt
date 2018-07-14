package me.danielaguilar.movietk

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import android.content.Intent
import android.support.design.widget.FloatingActionButton
import android.widget.Button
import me.danielaguilar.movietk.add_movie.AddMovieActivity
import me.danielaguilar.movietk.movie_list.MovieListActivity
import org.junit.Assert.assertEquals
import org.robolectric.Robolectric
import org.robolectric.Shadows.shadowOf


@RunWith(RobolectricTestRunner::class)
class MovieListActivityTest{

    @Test
    fun clickFloatButton_shouldStartAddMovieActivity(){
        val movieListActivity = Robolectric.setupActivity(MovieListActivity::class.java)
        val button = movieListActivity.findViewById(R.id.addMovie) as FloatingActionButton
        button.performClick()
        val expectedIntent = Intent(movieListActivity, AddMovieActivity::class.java)
        val actual = shadowOf(RuntimeEnvironment.application).nextStartedActivity
        assertEquals(expectedIntent.component, actual.component)
    }

}