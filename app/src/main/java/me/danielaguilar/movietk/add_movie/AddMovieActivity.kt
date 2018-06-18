package me.danielaguilar.movietk.add_movie

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_movie.*
import me.danielaguilar.movietk.R
import me.danielaguilar.movietk.data.Movie
import android.provider.MediaStore
import android.os.Environment
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import android.support.v4.content.FileProvider
import android.util.Log
import com.bumptech.glide.Glide
import me.danielaguilar.movietk.data.MovieViewModel


class AddMovieActivity : AppCompatActivity() {
    companion object {
        const val REQUEST_CAPTURE_IMAGE: Int = 1
    }
    private lateinit var imageFilePath: String
    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movie)
        viewModel   = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        clickMe.setOnClickListener {
            openCameraIntent()
        }
        saveMovie.setOnClickListener {

            val movie  = Movie(movieName.text.toString(),
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
    }

    private fun openCameraIntent() {
        val pictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (pictureIntent.resolveActivity(packageManager) != null) {
            //Create a file to store the image
            var photoFile: File? = null
            try {
                photoFile = createImageFile()
            } catch (ex: IOException) {
                // Error occurred while creating the File
            }

            if (photoFile != null) {
                val photoURI = FileProvider.getUriForFile(this, "com.example.android.provider", photoFile)
                pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        photoURI)
                startActivityForResult(pictureIntent,
                        REQUEST_CAPTURE_IMAGE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int,
                                  data: Intent?) {
        if (requestCode == REQUEST_CAPTURE_IMAGE) {
            Glide.with(this).load(imageFilePath).into(moviePictureUrl);
        }else if(resultCode == Activity.RESULT_CANCELED) {
            // User Cancelled the action
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(Date())
        val imageFileName = "IMG_" + timeStamp + "_"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(
                imageFileName, /* prefix */
                ".jpg", /* suffix */
                storageDir      /* directory */
        )

        imageFilePath = image.absolutePath
        return image
    }
}
