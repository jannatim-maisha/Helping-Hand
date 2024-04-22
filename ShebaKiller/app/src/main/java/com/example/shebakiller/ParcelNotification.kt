package com.example.shebakiller

import com.google.firebase.database.Exclude

data class ParcelNotification(var catagory:String="",var date:Long,var description:String="",var rid:String=""){
    @Exclude
    fun getMap():Map<String,Any?>{
        return mapOf(
            "catagory" to catagory,
            "date" to  date,
            "description" to description,
            "rid" to rid
        )
    }

}