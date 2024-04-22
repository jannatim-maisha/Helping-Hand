package com.example.shebakiller

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.io.File



class UserProfile : AppCompatActivity() {
    lateinit var imageView: ImageView
    lateinit var imageUri: Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        supportActionBar?.hide()

        val selectImage = findViewById<Button>(R.id.up_selectPhoto)
        val uploadImage =  findViewById<Button>(R.id.up_UploadPho)
        imageView = findViewById(R.id.up_img)

        val up_header= findViewById<TextView>(R.id.up_header)
        val up_userName = findViewById<TextView>(R.id.tg_name)
        val up_UserEmail = findViewById<TextView>(R.id.tg_people)
        val up_NIDBC = findViewById<TextView>(R.id.ft_Class)
        val up_dateOfBirth = findViewById<TextView>(R.id.tg_date)
        val up_PresentAddress= findViewById<TextView>(R.id.tg_start)
        val up_BloodGroup = findViewById<TextView>(R.id.tg_destination)
        val up_gender = findViewById<TextView>(R.id.tg_duration)
        val up_password= findViewById<TextView>(R.id.up_password)
        val up_editProfile=findViewById<Button>(R.id.tg_confirm)

        val ref = FirebaseAuth.getInstance()
        val database = FirebaseDatabase.getInstance()
        val databaseReference = database?.reference!!.child("profile")

        val currentUser = ref.currentUser
        val currentUserDb = databaseReference?.child((currentUser?.uid!!))

        currentUserDb?.get()?.addOnSuccessListener{
            if(it.exists()){
                val userName= it.child("name").value
                val mail = it.child("email").value
                val NIDBC = it.child("nidbc").value
                val dateOfBirth = it.child("dateOfBirth").value
                val PresentAddress = it.child("presentAddress").value
                val BloodGroup = it.child("bloodGroup").value
                val gender= it.child("gender").value
                val password = it.child("password").value


                up_userName.text = userName.toString()
                up_header.text = userName.toString()
                up_UserEmail.text = mail.toString()
                up_NIDBC.text = NIDBC.toString()
                up_dateOfBirth.text = dateOfBirth.toString()
                up_PresentAddress.text= PresentAddress.toString()
                up_BloodGroup.text = BloodGroup.toString()
                up_gender.text = gender.toString()
                up_password.text= password.toString()
            }

        }?.addOnFailureListener{
            it.printStackTrace()
        }

        up_editProfile.setOnClickListener {
            val userData = UserData(up_userName.text.toString(),up_UserEmail.text.toString(),up_NIDBC.text.toString(),up_dateOfBirth.text.toString(),up_PresentAddress.text.toString(),up_BloodGroup.text.toString(),up_gender.text.toString(),up_password.text.toString())

            currentUserDb?.setValue(userData)
                .addOnSuccessListener {

                    Toast.makeText(this, "Successfully Update Data", Toast.LENGTH_SHORT).show()

                }.addOnFailureListener {

                    it.printStackTrace() // Exception
                }

            val user = Firebase.auth.currentUser
            val newPassword = up_password.text.toString()
            // update password
            user!!.updatePassword(newPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        //write something
                    }
                }

            //val user = Firebase.auth.currentUser

            /*user!!.sendEmailVerification()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        //Log.d(TAG, "Email sent.")
                        Toast.makeText(this,"Sent",Toast.LENGTH_SHORT).show()
                    }
                }*/
            // update mail
            val new_mail = up_UserEmail.text.toString()
            user!!.updateEmail(new_mail)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        //Write something
                    }
                }
        }

        selectImage.setOnClickListener{
            selectImg()
        }


        uploadImage.setOnClickListener{
            uploadImg()
        }
        //-------------------------Read Profile Image---------------------------------------------------------------------------------
        val imgName=currentUser?.uid!!
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Fetching image....")
        progressDialog.setCancelable(false)
        progressDialog.show()
        val storageRef=FirebaseStorage.getInstance().reference.child("profile_img/$imgName")

        val localfile = File.createTempFile("tempImage","jpg")
        storageRef.getFile(localfile).addOnSuccessListener {

            if(progressDialog.isShowing){
                progressDialog.dismiss()
            }
            val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
            imageView.setImageBitmap(bitmap)

        }.addOnFailureListener{
            progressDialog.dismiss()
            //it.printStackTrace()
            Toast.makeText(this,"Fail to load Image From FireBase Storage",Toast.LENGTH_SHORT).show()
        }
        //-----------------------------------------------------------------------------------------------------------------------------------
    }

    private fun selectImg(){
        val intent = Intent()
        intent.type="image/*"
        intent.action= Intent.ACTION_GET_CONTENT

        startActivityForResult(intent,100)
    }
    private fun uploadImg(){
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Uploading File....")
        progressDialog.setCancelable(false)
        progressDialog.show()
        //val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        //val now = Date()
        val ref = FirebaseAuth.getInstance()
        val currentUser = ref.currentUser
        val fileName = currentUser?.uid!!
        val storageReference = FirebaseStorage.getInstance().getReference("profile_img/$fileName")

        storageReference.putFile(imageUri).addOnSuccessListener {
            imageView.setImageURI(null)
            Toast.makeText(this,"Successfully Uploaded",Toast.LENGTH_SHORT).show()
            if(progressDialog.isShowing){
                progressDialog.dismiss()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==100 && resultCode== RESULT_OK){
            imageUri= data?.data!!
            imageView.setImageURI(imageUri)

        }
    }
}