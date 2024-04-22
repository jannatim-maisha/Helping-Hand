package com.example.shebakiller.ui

import android.media.Image
import android.widget.ImageView

class ModelNoti {
    //variables
    var notiid:String = ""
    var rid:String = ""
    var catagory:String = ""
    var description:String = ""
    //var catagoryId:String = ""
    //var url:String = ""
    var date:Long = 0
    //var viewCount:Long = 0
    //var downloadsCount:Long =0


    //empty constructor (required by firebase)
    constructor()



    //parameterized constructor
    constructor(
        notiid: String,
        rid: String,

        catagory: String,
        description: String,

        date: Long
    ) {
        this.notiid = notiid
        this.catagory = catagory
        this.description = description
        this.date = date
        this.rid = rid
    }

}
