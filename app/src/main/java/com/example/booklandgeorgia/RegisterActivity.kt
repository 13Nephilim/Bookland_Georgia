package com.example.booklandgeorgia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var nname: EditText
    private lateinit var eemail: EditText
    private lateinit var ppassword: EditText
    private lateinit var cconfirmPassword: EditText
    private lateinit var register: Button
    private lateinit var alreadyRegistered: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        init()
        registerListeners()

    }

    private fun init() {
        nname = findViewById(R.id.editTextTextPersonName)
        eemail = findViewById(R.id.inputEmail)
        ppassword = findViewById(R.id.inputPassword)
        cconfirmPassword = findViewById(R.id.confirmPassword)
        register = findViewById(R.id.buttonRegister)
        alreadyRegistered = findViewById(R.id.alreadyHaveAcc)
    }

    private fun registerListeners(){


        alreadyRegistered.setOnClickListener{
            val intent = Intent(this, VeryFirstPageActivity::class.java)
            startActivity(intent)
        }


        register.setOnClickListener{

            val email = eemail.text.toString()
            val password = ppassword.text.toString()
            val password2 = cconfirmPassword.text.toString()
            val name = nname.text.toString()

            if (email.isEmpty()) {
            eemail.error = "შეიყვანეთ მეილი!"
            return@setOnClickListener
            }

            else if (name.isEmpty()){
            nname.error = "შეიყვანეთ სახელი!"
            return@setOnClickListener
            }

            else if (password != password2) {
            Toast.makeText(this, "პაროლები უნდა ემთხვეოდეს ერთმანეთს!", Toast.LENGTH_SHORT).show()
            return@setOnClickListener
            }

            else if (email.length<8){
            eemail.error = "მეილი არასწორადაა შეყვანილი"
            return@setOnClickListener
            }

            else if (!email.contains("@") || !email.contains(".")){
            eemail.error = "მეილი არასწორადაა შეყვანილი"
            }

            else if (password.isEmpty()){
            ppassword.error = "შეიყვანეთ პაროლი"
            return@setOnClickListener
            }

            else if (password2.isEmpty()) {
            cconfirmPassword.error = "შეიყვანეთ პაროლი"
            return@setOnClickListener
            }

            else if(password.length<8){
            ppassword.error = "პაროლი უნდა შეიცავდეს მინიმუმ 8 სიმბოლოს"
            return@setOnClickListener
            }


            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, VeryFirstPageActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "სამწუხაროდ თქვენ ვერ გაიარეთ რეგისტრაცია!", Toast.LENGTH_SHORT).show()
                    }

                }

        }
    }   }

