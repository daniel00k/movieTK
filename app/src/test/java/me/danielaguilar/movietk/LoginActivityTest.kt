package me.danielaguilar.movietk

import android.content.Intent
import android.widget.Button
import me.danielaguilar.movietk.login.LoginActivity
import me.danielaguilar.movietk.movie_list.MovieListActivity
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf


@RunWith(RobolectricTestRunner::class)
class LoginActivityTest{

    @Test
    fun clickLogin_shouldStartMovieListActivity(){
        val loginActivity = Robolectric.setupActivity(LoginActivity::class.java)
        val button = loginActivity.findViewById(R.id.email_sign_in_button) as Button
        button.performClick()
        val expectedIntent = Intent(loginActivity, MovieListActivity::class.java)
        val actual = shadowOf(RuntimeEnvironment.application).nextStartedActivity
        assertEquals(expectedIntent.component, actual.component)
    }

}