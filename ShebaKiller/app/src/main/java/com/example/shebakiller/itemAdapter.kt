package com.example.shebakiller

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import java.security.AccessControlContext
import java.text.FieldPosition

class itemAdapter(var context: Context,var arrayList: ArrayList<electronicsItems>): BaseAdapter() {

    override fun getItem(position: Int): Any {

        return arrayList.get(position)
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    } 
    
    
    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view:View = View.inflate(context, R.layout.repair_items, null)
        var icons:ImageView = view.findViewById(R.id.icons)
        var names: TextView = view.findViewById(R.id.name_icons)
        var electronicsItems: electronicsItems = arrayList.get(position)
        icons.setImageResource(electronicsItems.icons!!)
        names.text = electronicsItems.name

        return view
    }

}

