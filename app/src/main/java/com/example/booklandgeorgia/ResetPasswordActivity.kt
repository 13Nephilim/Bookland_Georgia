package com.example.booklandgeorgia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var btnSend: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        init()
        registerListeners()


    }

    private fun init() {
        email = findViewById(R.id.inputEmail)
        btnSend = findViewById(R.id.buttonSend)
    }

    private fun registerListeners() {
        btnSend.setOnClickListener{
            val email = email.text.toString()

            if (email.isEmpty()) {
                Toast.makeText(this, "ჩაწერეთ ელ.ფოსტა!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance()
                .sendPasswordResetEmail(email)
                .addOnCompleteListener{task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "პაროლი განახლდა! შეამოწმეთ ელ-ფოსტა.", Toast.LENGTH_SHORT).show()
                    } else{
                        Toast.makeText(this, "სამწუხაროდ მონაცემთა გაგზავნა ვერ მოხერხდა!", Toast.LENGTH_SHORT).show()
                    }
                }

        }
    }

}