package com.example.shebakiller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ParcelDelivery : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parcel_delivery)
        /*Reciever_Name
Reciever_Phone
Pickup_location
Dropoff_location
confirm_button
cancel_button*/

        val reciever_name= findViewById<TextView>(R.id.tg_DP)
        val reciever_phone= findViewById<TextView>(R.id.tg_SP)
        val pickUp_location= findViewById<TextView>(R.id.tg_date)
        val dropOff =findViewById<TextView>(R.id.tg_Dur)
        val confirmButton = findViewById<Button>(R.id.tg_confirm)
        val cancelButton = findViewById<Button>(R.id.tg_cancel)
        val timestamp = System.currentTimeMillis()

        var des1=pickUp_location.text.toString()
        var des2=dropOff.text.toString()
        var root = "$des1$des2"
        val ref = FirebaseAuth.getInstance()
        val currentUser = ref.currentUser?.uid
        val myRef = Firebase.database.reference.child("Parcel_Delivery").child((currentUser.toString()))
        val myRef1 = Firebase.database.reference.child("Notification").child((timestamp.toString()))
        confirmButton.setOnClickListener {
            val user = User(reciever_name.text.toString().trim(),reciever_phone.text.toString().trim(),pickUp_location.text.toString().trim(),dropOff.text.toString().trim())
            myRef.setValue(user).addOnSuccessListener {
                Toast.makeText(this,"Request Sent",Toast.LENGTH_LONG).show()
            }.addOnFailureListener{
                Toast.makeText(this,"Failed",Toast.LENGTH_LONG).show()
            }

            val parcelNotification= ParcelNotification("Parcel Delivery",timestamp,"From "+pickUp_location.text.toString().trim()+" To "+dropOff.text.toString().trim(),currentUser.toString())
            myRef1.setValue(parcelNotification).addOnSuccessListener {

            }
        }
        cancelButton.setOnClickListener {
            startActivity(Intent(this@ParcelDelivery,Home::class.java))
        }

    }
}