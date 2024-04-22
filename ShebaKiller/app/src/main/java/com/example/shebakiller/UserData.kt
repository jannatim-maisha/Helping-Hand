package com.example.shebakiller

import com.google.firebase.database.Exclude

data class UserData(val name:String ="",val email:String="",val NIDBC:String ="", val dateOfBirth:String ="", val PresentAddress:String ="", val BloodGroup:String ="", val gender:String ="", val password:String ="" ){

    // function to update data
    @Exclude
    fun getMap():Map<String,Any?>{
        return mapOf(
            "name" to name,
            "email" to email,
            "NIDBC" to NIDBC,
            "dateOfBirth" to dateOfBirth,
            "PresentAddress" to PresentAddress,
            "BloodGroup" to BloodGroup,
            "gender" to gender,
            "password" to password
        )
    }
}