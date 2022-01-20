package com.example.booklandgeorgia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class VeryFirstPageActivity : AppCompatActivity() {

    private lateinit var eemail: EditText
    private lateinit var ppassword: EditText
    private lateinit var login: Button
    private lateinit var passwordReset: TextView
    private lateinit var register: TextView
    private lateinit var guest: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        if (FirebaseAuth.getInstance().currentUser != null) {
            gotoprofile()
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_very_first_page)

        init()

        registerListeners()

    }

    private fun gotoprofile() {
        val intent = Intent(this, MenuPageActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun init() {
        eemail = findViewById(R.id.inputEmail)
        ppassword = findViewById(R.id.inputPassword)
        login = findViewById(R.id.buttonLogin)
        passwordReset = findViewById(R.id.forgotPassword)
        register = findViewById(R.id.goToRegister)
        guest = findViewById(R.id.loginGuest)
    }

    private fun registerListeners() {
        register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        passwordReset.setOnClickListener {
            val intent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(intent)
        }


        login.setOnClickListener {
            val email = eemail.text.toString()
            val password = ppassword.text.toString()

            if (email.isEmpty()) {
                eemail.error = "შეავსეთ ველები"
                return@setOnClickListener
            } else if (email.length < 8) {
                eemail.error = "მეილი არასწორადაა შეყვანილი"
                return@setOnClickListener
            } else if (!email.contains("@") || !email.contains(".")) {
                eemail.error = "მეილი არასწორადაა შეყვანილი"
            } else if (password.isEmpty()) {
                ppassword.error = "შეავსეთ პაროლი!"
                return@setOnClickListener
            } else if (password.length < 8) {
                ppassword.error = "პაროლი უნდა შეიცავდეს მინიმუმ 8 სიმბოლოს"
                return@setOnClickListener
            }

            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, MenuPageActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this,
                            "მოცემული ანგარიში არ არსებობს!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }


                }

        }


        guest.setOnClickListener{
            val intent = Intent(this, MenuPageActivity::class.java)
            startActivity(intent)
        }
    }}