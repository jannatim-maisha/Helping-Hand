package com.example.shebakiller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class blood_donation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blood_donation)
        var bloodGroup:String=""
        val request = findViewById<Button>(R.id.blood_ok)
        val radioGroup =findViewById<RadioGroup>(R.id.bloodGroup)
        val radioGroup2 =findViewById<RadioGroup>(R.id.bloodGroup2)
        val hospitalName= findViewById<TextView>(R.id.HospitalName)
        val timestamp = System.currentTimeMillis()

        val ref = FirebaseAuth.getInstance()
        val currentUser = ref.currentUser?.uid
        val myRef = Firebase.database.reference.child("Blood_Donation").child((currentUser.toString()))
        val myRef1 = Firebase.database.reference.child("Notification").child((timestamp.toString()))

        radioGroup.setOnCheckedChangeListener { radioGroup, i ->
            var rb = findViewById<RadioButton>(i)
            if(rb!=null){
                Toast.makeText(this,rb.text.toString(),Toast.LENGTH_SHORT).show()
                bloodGroup=rb.text.toString()  // reading blood group from radio button
            }
        }

        radioGroup2.setOnCheckedChangeListener { radioGroup2, i ->
            var rb = findViewById<RadioButton>(i)
            if(rb!=null){
                Toast.makeText(this,rb.text.toString(),Toast.LENGTH_SHORT).show()
                bloodGroup=rb.text.toString()  // reading blood group from radio button
            }
        }



            request.setOnClickListener {
            myRef.child("Blood Group").setValue(bloodGroup).addOnSuccessListener {

            }.addOnFailureListener{
                Toast.makeText(this,"Failed To Save Data",Toast.LENGTH_LONG).show()
            }
            myRef.child("Hospital Name").setValue(hospitalName.text.toString()).addOnSuccessListener {

            }.addOnFailureListener{
                Toast.makeText(this,"Failed To Save Data",Toast.LENGTH_LONG).show()
            }
            val parcelNotification= ParcelNotification("Blood Donation",timestamp,hospitalName.text.toString().trim()+" Hospital Need "+bloodGroup+" Blood",currentUser.toString())
            myRef1.setValue(parcelNotification).addOnSuccessListener {

            }.addOnFailureListener{
                Toast.makeText(this,"Failed To Save Data",Toast.LENGTH_LONG).show()
            }
            Toast.makeText(this,"Request Sent",Toast.LENGTH_SHORT).show()
        }
    }
}