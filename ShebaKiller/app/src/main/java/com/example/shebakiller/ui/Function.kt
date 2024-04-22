package com.example.shebakiller.ui

import android.app.Application
import android.app.ProgressDialog
import android.content.Context
import android.text.format.DateFormat
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storageMetadata
import java.sql.Timestamp
import java.util.*

class Function:Application() {
    override fun onCreate() {
        super.onCreate()
    }

    companion object{

        //created a static method to convert timestamp to proper date format, so we can use it everywhere in project, no need to rewrite again
        fun formatTimeStamp(timestamp: Long) : String{
            val cal = Calendar.getInstance(Locale.ENGLISH)
            cal.timeInMillis = timestamp

            //format dd/MM/yyyy
            return DateFormat.format("dd/MM/yyyy",cal).toString()
        }


        /*instead of making new function loadPdfPageCount() to just load pages count it would be more good to use some existing function to do that
        * i.e. loadPdfFromUrlSinglePage
        * we will add another parameter of type textview e.g.pageBar
        * whenever we call that function
        *   1)if we require page number we will pass pagesBar (TextView)
        *   2)if we don't require page number we will pass null
        * and in function if pagesBar (TextView) parameter is not null we will set the page number count
        * */




    }


}
