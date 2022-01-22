package com.example.booklandgeorgia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class MenuPageActivity : AppCompatActivity() {

    private lateinit var allBook: Button
    private lateinit var categories: Button
    private lateinit var profile: Button
    private lateinit var moreInfo: Button
    private lateinit var exit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_page)

        init()

        registerListeners()

    }

    private fun init() {
        allBook = findViewById(R.id.allBooks)
        categories = findViewById(R.id.categories)
        profile = findViewById(R.id.profile)
        moreInfo = findViewById(R.id.moreInfo)
        exit = findViewById(R.id.logoutBtn)
    }

    private fun registerListeners() {
        allBook.setOnClickListener {
            val intent = Intent(this, AllBooksActivity::class.java)
            startActivity(intent)
        }

        categories.setOnClickListener {
            val intent = Intent(this, CategoriesActivity::class.java)
            startActivity(intent)
        }

        profile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        moreInfo.setOnClickListener {
            val intent = Intent(this, MoreInfoActivity::class.java)
            startActivity(intent)
        }

        exit.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, VeryFirstPageActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}