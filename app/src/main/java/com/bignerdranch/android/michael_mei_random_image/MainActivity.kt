package com.bignerdranch.android.michael_mei_random_image

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    private lateinit var loadImageButton: Button
    private lateinit var imageView: ImageView
    private lateinit var editText: EditText

    private val TAG = "MainActivity"

    private val imageList = listOf(
        R.drawable.baseline_10k_24,
        R.drawable.baseline_10mp_24,
        R.drawable.baseline_11mp_24,
        R.drawable.baseline_assistant_24,
        R.drawable.baseline_bluetooth_24,
        R.drawable.baseline_battery_saver_24
    )

    private lateinit var sharedPreferences: SharedPreferences
    private val SHARED_PREFS_KEY_IMAGE = "image"
    private val SHARED_PREFS_KEY_TEXT = "text"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate() called")

        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

        loadImageButton = findViewById(R.id.loadImageButton)
        imageView = findViewById(R.id.imageView)
        editText = findViewById(R.id.editText)

        loadImageButton.setOnClickListener {
            loadImage()
        }


        val savedImageId = sharedPreferences.getInt(SHARED_PREFS_KEY_IMAGE, -1)
        if (savedImageId == -1) {
            imageView.setImageResource(R.drawable.baseline_10k_24)
        } else {
            imageView.setImageResource(savedImageId)
        }

        val savedText = sharedPreferences.getString(SHARED_PREFS_KEY_TEXT, "")
        editText.setText(savedText)
    }

    override fun onDestroy() {
        super.onDestroy()

        val text = editText.text.toString()
        sharedPreferences.edit().putString(SHARED_PREFS_KEY_TEXT, text).apply()

        Log.d(TAG, "onDestroy() called")
    }

    private fun loadImage() {
        val imageId = imageList.random()
        imageView.setImageResource(imageId)

        // Save image state
        sharedPreferences.edit().putInt(SHARED_PREFS_KEY_IMAGE, imageId).apply()
    }
}