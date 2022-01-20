package com.example.booklandgeorgia

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MoreInfoActivity : AppCompatActivity() {

    private lateinit var facebook: ImageView
    private lateinit var instagram: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_info)

        init()

        registerListeners()

    }

    private fun init() {
        facebook = findViewById(R.id.btnFacebook)
        instagram = findViewById(R.id.btnInstagram)
    }

    private fun registerListeners() {
        facebook.setOnClickListener {
            var intent = Intent (Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/Nephilimm13"))
            startActivity(intent)
        }

        instagram.setOnClickListener {
            var intent = Intent (Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/13nephilim/"))
            startActivity(intent)
        }

    }


}