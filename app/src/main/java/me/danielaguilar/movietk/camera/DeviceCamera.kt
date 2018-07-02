package me.danielaguilar.movietk.camera

import android.app.Activity
import android.content.Intent
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

object DeviceCamera {

    fun openCameraIntent(activity: Activity, actionId: Int, callback: (absolutePath: String) -> Unit) {
        val pictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (pictureIntent.resolveActivity(activity.packageManager) != null) {
            //Create a file to store the image
            var photoFile: File? = null
            try {
                photoFile = createImageFile(activity)
                callback(photoFile.absolutePath)

            } catch (ex: IOException) {
                // Error occurred while creating the File
            }

            if (photoFile != null) {
                val photoURI = FileProvider.getUriForFile(activity, "com.example.android.provider", photoFile)
                pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        photoURI)
                activity.startActivityForResult(pictureIntent,
                        actionId)
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(activity: Activity): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(Date())
        val imageFileName = "IMG_" + timeStamp + "_"
        val storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        return File.createTempFile(
                imageFileName, /* prefix */
                ".jpg", /* suffix */
                storageDir      /* directory */
        )
    }

}