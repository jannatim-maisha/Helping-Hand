package com.example.shebakiller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {

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

    private val ref = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance()
    var databaseReference = database?.reference!!.child("profile")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        supportActionBar?.hide()

        val name = findViewById<TextView>(R.id.UserName)
        val email=findViewById<TextView>(R.id.tg_people)
        val passWord = findViewById<TextView>(R.id.UserPassword)
        val button =findViewById<Button>(R.id.button)
        val login = findViewById<Button>(R.id.btnUpToIn)

        button.setOnClickListener {
            val emails = email.text.toString().trim()

            if(!isValidString(emails)){

                email.error="Invalid Email-Format"
                return@setOnClickListener
            }

            val psd = passWord.text.toString().trim()

            if(emails.isEmpty()){

                email.error="Email Required"
                return@setOnClickListener
            }

            if(psd.isEmpty()){

                passWord.error="Password Required"
                return@setOnClickListener
            }

            ref.createUserWithEmailAndPassword(
                emails,
                psd
            ).addOnCompleteListener {
                if(it.isSuccessful){
                    val currentUser = ref.currentUser
                    val currentUserDb = databaseReference?.child((currentUser?.uid!!))
                    val userName=name.text.toString()
                    currentUserDb.child("name").setValue(userName)
                    currentUserDb.child("email").setValue(emails)
                    currentUserDb.child("password").setValue(psd)

                }
            }.addOnSuccessListener {
                Toast.makeText(this,"Sign-up Successful", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener(){

                Toast.makeText(this,"Sign-up Failed",Toast.LENGTH_SHORT).show()
            }

        }

        login.setOnClickListener {
            startActivity(Intent(this@SignUpActivity,LoginActivity::class.java))
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
        }
    }
}