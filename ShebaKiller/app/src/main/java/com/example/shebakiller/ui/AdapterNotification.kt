package com.example.shebakiller.ui


import android.app.Notification
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.shebakiller.Home
import com.example.shebakiller.R
import com.example.shebakiller.databinding.RowNotificationBinding
import com.example.shebakiller.ui.slideshow.NotificationAccept

class AdapterNotification : RecyclerView.Adapter<AdapterNotification.HolderNotification> {

        //context, get using constructor
        private var context: Context

        //arrayList to hold pdfs, get using constructor
        private var pdfArrayList: ArrayList<ModelNoti>

        //viewBinding row pdf user xml
        private lateinit var binding: RowNotificationBinding

        constructor(context: Context, pdfArrayList: ArrayList<ModelNoti>) {
            this.context = context
            this.pdfArrayList = pdfArrayList
        }




        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderNotification {
            binding = RowNotificationBinding.inflate(LayoutInflater.from(context),parent,false)

            return HolderNotification(binding.root)
        }

        override fun onBindViewHolder(holder: HolderNotification, position: Int) {
            //get data
            val model = pdfArrayList[position]
            //catagory?
            val title = model.catagory
            val description = model.description
            val notiid = model.notiid
            val date = model.date
            val rid = model.rid
            //convert time
            val sdate = Function.formatTimeStamp(date)

            //set data


            holder.titleBar.text = title
            holder.descriptionBar.text = description
            holder.dateBar.text = sdate



            //handle click
            holder.itemView.setOnClickListener {
                val builder = AlertDialog.Builder(this.context)
                builder.setMessage("Do you want to accept the request?")
                builder.setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
                    dialogInterface.cancel()
                })
                builder.setPositiveButton("Yes",DialogInterface.OnClickListener { dialogInterface, i ->
                    val intent = Intent(context, AcceptAndConnect::class.java)
                    intent.putExtra("title", title)
                    intent.putExtra("description", description)
                    intent.putExtra("sdate", sdate)
                    intent.putExtra("rid", rid)
                    context.startActivity(intent)

                })
                val alertDialog = builder.create()
                alertDialog.show()
            }

        }



    override fun getItemCount(): Int {
            return pdfArrayList.size
        }



        //view holder class row pdf student
        inner class HolderNotification(itemView: View): RecyclerView.ViewHolder(itemView){
            //init UI components from row pdf student xml
            var titleBar = binding.titleBar
            var descriptionBar = binding.descriptionBar
            var noticeBar = binding.noticeBar
            //catagory?
            var dateBar = binding.dateBar



        }

}
