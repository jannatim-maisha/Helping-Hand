package com.example.shebakiller

import com.google.firebase.database.Exclude

data class ClintData(val uid:String ="",val accepted:Boolean,val BloodGroup:String="",val acceptedBy:String=""){

    // function to update data
    @Exclude
    fun getMap():Map<String,Any?>{
        return mapOf(
            "uid" to uid,
            "accepted" to accepted,
            "acceptedBy" to acceptedBy,
            "BloodGroup" to BloodGroup
        )
    }
}


