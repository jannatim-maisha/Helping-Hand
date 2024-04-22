package com.example.shebakiller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    val EMAIL_ADDRESS_PATTERN = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    fun isValidString(str: String): Boolean{
        return EMAIL_ADDRESS_PATTERN.matcher(str).matches()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val ref = FirebaseAuth.getInstance()

        val signUp = findViewById<Button>(R.id.btnInToUp)
        val login  = findViewById<Button>(R.id.button)
        val email  = findViewById<TextView>(R.id.tg_name)
        val password= findViewById<TextView>(R.id.up_password)

        //if signUp button press
        signUp.setOnClickListener {
            startActivity(Intent(this@LoginActivity,SignUpActivity::class.java))
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        }

        // if login button press
        login.setOnClickListener {

            val emails = email.text.toString()
            if(!isValidString(emails)){

                email.error="Invalid Email-Format"
                return@setOnClickListener
            }
            val psd = password.text.toString().trim()

            if(emails.isEmpty()){

                email.error="Email Required"
                return@setOnClickListener
            }
            if(psd.isEmpty()){

                password.error="Password Required"
                return@setOnClickListener
            }

            ref.signInWithEmailAndPassword(emails,psd).addOnSuccessListener {
                Toast.makeText(this,"successfully Log-In", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@LoginActivity,Home::class.java))

            }.addOnFailureListener{
                it.printStackTrace()
            }
        }
    }
}