package com.example.shebakiller.ui

import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.shebakiller.R
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.io.File
import java.util.jar.Manifest

class AcceptAndConnect : AppCompatActivity() {

    private var title = ""
    private var description = ""
    private var date = ""
    private var rid = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accept_and_connect)



        this.title = intent.getStringExtra("title")!!
        this.description = intent.getStringExtra("description")!!
        this.date = intent.getStringExtra("sdate")!!
        this.rid = intent.getStringExtra("rid")!!

        var ttext = findViewById<TextView>(R.id.cattext)
        var tdes = findViewById<TextView>(R.id.des)
        var tdate = findViewById<TextView>(R.id.date)
        var tphone = findViewById<TextView>(R.id.ReqPhone)
        var tname = findViewById<TextView>(R.id.Reqname)
        var timg = findViewById<ImageView>(R.id.catimage)

        ttext.text = "$title"
        tdes.text = "$description"
        tdate.text = "$date"

        val myRef = Firebase.database.reference.child("profile").child(rid)

        myRef.get().addOnSuccessListener {
            if(it.exists()){
                var uname = it.child("name").value.toString()
                var phone = it.child("nidbc").value.toString()
                tphone.text = phone
                tname.text = uname


                var btn = findViewById<Button>(R.id.calbtn)

                btn.setOnClickListener{
                    val phoneNo = phone
                    val callIntent = Intent(Intent.ACTION_CALL)
                    callIntent.data = Uri.parse("tel:" + phone)
                    startActivity(callIntent)
                }
            }
        }


        val imgName=rid
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Fetching image....")
        progressDialog.setCancelable(false)
        progressDialog.show()
        val storageRef= FirebaseStorage.getInstance().reference.child("profile_img/$imgName")

        val localfile = File.createTempFile("tempImage","jpg")
        storageRef.getFile(localfile).addOnSuccessListener {

            if(progressDialog.isShowing){
                progressDialog.dismiss()
            }
            val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
            timg.setImageBitmap(bitmap)

        }.addOnFailureListener{
            progressDialog.dismiss()
            //it.printStackTrace()
            Toast.makeText(this,"Fail to load Image From FireBase Storage",Toast.LENGTH_SHORT).show()
        }







    }



}

private fun ImageView.setImageDrawable(blood: Int) {
    TODO("Not yet implemented")
}
