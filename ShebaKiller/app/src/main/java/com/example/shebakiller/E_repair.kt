package com.example.shebakiller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class E_repair : AppCompatActivity(),AdapterView.OnItemClickListener {

    private var gridView:GridView ? = null
    private var arrayList:ArrayList<electronicsItems> ? = null
    private var itemAdapter:itemAdapter ? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_erepair)

        gridView = findViewById(R.id.e_repair)
        arrayList = ArrayList()
        arrayList = setDataList()
        itemAdapter = itemAdapter(applicationContext,arrayList!!)
        gridView?.adapter=itemAdapter
        gridView?.onItemClickListener=this
    }
   private fun setDataList() :ArrayList<electronicsItems>{

       var arrayList:ArrayList<electronicsItems> = ArrayList()

       arrayList.add(electronicsItems(R.drawable.ac1,"AC Repair"))
       arrayList.add(electronicsItems(R.drawable.tv,"TV Repair"))
       arrayList.add(electronicsItems(R.drawable.computer,"Computer Repair"))
       arrayList.add(electronicsItems(R.drawable.frige,"Refrigerator Repair"))
       arrayList.add(electronicsItems(R.drawable.laptop,"Laptop Repair"))
       arrayList.add(electronicsItems(R.drawable.motherboard,"Motherboard Repair"))
       arrayList.add(electronicsItems(R.drawable.oven,"Oven Repair"))
       arrayList.add(electronicsItems(R.drawable.remote1,"remote Repair"))
       arrayList.add(electronicsItems(R.drawable.ricecooker,"RiceCooker Repair"))
       arrayList.add(electronicsItems(R.drawable.washing_machine,"Washing Machine Repair"))
       arrayList.add(electronicsItems(R.drawable.router,"WIFI Router Repair"))
       arrayList.add(electronicsItems(R.drawable.pipe,"Pipeline Repair"))
       arrayList.add(electronicsItems(R.drawable.light,"Light Repair"))
       arrayList.add(electronicsItems(R.drawable.fan,"Fan Repair"))
       arrayList.add(electronicsItems(R.drawable.car,"Car Repair"))
       arrayList.add(electronicsItems(R.drawable.bicycle,"Cycle Repair"))
       arrayList.add(electronicsItems(R.drawable.bike,"Bike Repair"))

       return arrayList

   }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var electronicsItems:electronicsItems=arrayList!!.get(position)

        val ref = FirebaseAuth.getInstance()
        val currentUser = ref.currentUser?.uid
        val temp = electronicsItems.name.toString()
        val timestamp = System.currentTimeMillis()
        val myRef1 = Firebase.database.reference.child("Notification").child((timestamp.toString()))
        val parcelNotification= ParcelNotification(temp, timestamp,"Request for $temp",currentUser.toString())
        myRef1.setValue(parcelNotification).addOnSuccessListener {

            Toast.makeText(applicationContext, electronicsItems.name,Toast.LENGTH_LONG).show()
        }

    }

}

