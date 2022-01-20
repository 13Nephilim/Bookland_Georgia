package com.example.booklandgeorgia


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.booklandgeorgia.dataclasses.UserInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.DatabaseReference

class ProfileActivity : AppCompatActivity() {

    private val auth = FirebaseAuth.getInstance()
    private val dbUserInfo: DatabaseReference = FirebaseDatabase.getInstance().getReference("UserInfo")
    private val dbBooks: DatabaseReference = FirebaseDatabase.getInstance().getReference("Book")

    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    private lateinit var editTextPersonName: TextView
    private lateinit var editTextUrl: TextView
    private lateinit var button: Button
    private lateinit var btnChange: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        init()

        registerListeners()

        dbUserInfo.child(auth.currentUser?.uid!!).addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val userInfo = snapshot.getValue(UserInfo::class.java) ?: return

                textView.text = userInfo.name

                Glide.with(this@ProfileActivity)
                    .load(userInfo.imageUrl)
                    .into(imageView)

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    private fun init() {
        imageView = findViewById(R.id.imageView5)
        textView = findViewById(R.id.textView5)
        editTextPersonName = findViewById(R.id.editTextPersonName)
        editTextUrl = findViewById(R.id.editTextUrl)
        button = findViewById(R.id.button13)
        btnChange = findViewById(R.id.buttonChange)
    }


    private fun registerListeners() {

        btnChange.setOnClickListener {
            val intent = Intent(this, PasswordChangeActivity::class.java)
            startActivity(intent)
            finish()
        }


        button.setOnClickListener {

            val name = editTextPersonName.text.toString()
            val url = editTextUrl.text.toString()

            val userInfo = UserInfo(name, url)


            dbUserInfo.child(auth.currentUser?.uid!!).setValue(userInfo)

        }

    }





}

