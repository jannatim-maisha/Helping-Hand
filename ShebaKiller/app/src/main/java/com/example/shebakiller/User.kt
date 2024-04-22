package com.example.shebakiller

import com.google.firebase.database.Exclude

data class User(var name:String="",var contact_no:String="",var pick_up:String="",var drop_off:String=""){
    @Exclude
    fun getMap():Map<String,Any?>{
        return mapOf(
            "name" to name,
            "contact_no" to contact_no,
            "pick_up" to pick_up,
            "drop_off" to drop_off

        )
    }
}
