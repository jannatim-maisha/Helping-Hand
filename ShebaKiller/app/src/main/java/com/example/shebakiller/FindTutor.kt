package com.example.shebakiller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FindTutor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_tutor)
        val name= findViewById<TextView>(R.id.tg_name)
        val ftbutton= findViewById<Button>(R.id.tg_confirm)
        ftbutton.setOnClickListener {

            val ref = FirebaseAuth.getInstance()
            val currentUser = ref.currentUser?.uid
            val timestamp = System.currentTimeMillis()
            val myRef1 = Firebase.database.reference.child("Notification").child((timestamp.toString()))
            val parcelNotification= ParcelNotification("Find Tutor", timestamp,name.text.toString()+" wants a teacher",currentUser.toString())
            myRef1.setValue(parcelNotification).addOnSuccessListener {

                Toast.makeText(this, "Request Sent Successfully", Toast.LENGTH_LONG).show()
            }
        }


    }
}