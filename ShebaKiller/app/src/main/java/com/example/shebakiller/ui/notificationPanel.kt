package com.example.shebakiller.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.example.shebakiller.R
import com.example.shebakiller.databinding.ActivityNotificationPanelBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class notificationPanel : AppCompatActivity() {

    //view binding
    private lateinit var binding: ActivityNotificationPanelBinding

    private companion object{
        const val TAG = "Notification_list"
    }

    //catagory id, title
    //private var catagoryId = ""
    //private var catagory = ""

    //arraylist holding books
    private lateinit var pdfArrayList: ArrayList<ModelNoti>
    //adapter
    private lateinit var adapterPdfStudent: AdapterNotification


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationPanelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadPdfList()


    }



    private fun loadPdfList() {
        pdfArrayList = ArrayList()

        val ref = FirebaseDatabase.getInstance().getReference("Notification")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //clear list before start adding data into it
                pdfArrayList.clear()
                for(ds in snapshot.children){
                    //get data
                    val model = ds.getValue(ModelNoti::class.java)
                    //add to list
                    if (model != null) {
                        pdfArrayList.add(model)
                        Log.d(TAG,"onDataChange: ${model.catagory} ")

                    }

                }
                //setup adapter
                adapterPdfStudent = AdapterNotification(this@notificationPanel,pdfArrayList)
                binding.pdfsRv.adapter = adapterPdfStudent

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }


}