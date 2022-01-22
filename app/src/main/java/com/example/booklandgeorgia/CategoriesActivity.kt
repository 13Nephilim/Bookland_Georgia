package com.example.booklandgeorgia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class CategoriesActivity : AppCompatActivity() {

    private lateinit var history: TextView
    private lateinit var adventure: TextView
    private lateinit var fantasy: TextView
    private lateinit var detective: TextView
    private lateinit var unknown: TextView
    private lateinit var psycho: TextView
    private lateinit var sport: TextView
    private lateinit var horror: TextView
    private lateinit var back: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        init()

        registerListeners()

    }

    private fun init() {
        history = findViewById(R.id.history)
        adventure = findViewById(R.id.adventure)
        fantasy = findViewById(R.id.fantasy)
        detective = findViewById(R.id.detective)
        unknown = findViewById(R.id.unknown)
        psycho = findViewById(R.id.psychological)
        sport = findViewById(R.id.sport)
        horror = findViewById(R.id.horror)
        back = findViewById(R.id.btnBack)
    }

    private fun registerListeners() {
        back.setOnClickListener {
            val intent = Intent(this, MenuPageActivity::class.java)
            startActivity(intent)
        }
    }

}