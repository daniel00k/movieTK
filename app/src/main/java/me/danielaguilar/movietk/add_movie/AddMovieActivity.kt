package me.danielaguilar.movietk.add_movie

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_movie.*
import me.danielaguilar.movietk.R

class AddMovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movie)
        saveMovie.setOnClickListener {
            val intent = Intent()
            intent.putExtra("movieName", movieName.text.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}
