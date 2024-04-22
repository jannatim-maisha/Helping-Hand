package com.example.shebakiller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.shebakiller.ui.notificationPanel
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class Home : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val drawerLayout : DrawerLayout =findViewById(R.id.drawerLayout)
        val navView : NavigationView =findViewById(R.id.nav_view)
        val donateBlood= findViewById<ImageButton>(R.id.imageButtonDonateBlood)
        val rider= findViewById<ImageButton>(R.id.imageButtonRider)
        val repair= findViewById<ImageButton>(R.id.imageButtonRepair)
        val parcel= findViewById<ImageButton>(R.id.imageButton4)
        val findtutor= findViewById<ImageButton>(R.id.imageButtonfind_tutor)
        val tourguide= findViewById<ImageButton>(R.id.imageButtontour_guide)

        toggle= ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener{
            when(it.itemId){
                R.id.nav_home->startActivity(Intent(this@Home,Nav_header::class.java))//Toast.makeText(applicationContext,"Clicked Home",Toast.LENGTH_SHORT).show()
                R.id.nav_message->Toast.makeText(applicationContext,"Clicked Message",Toast.LENGTH_SHORT).show()
                R.id.nav_notifications-> {startActivity(Intent(this@Home,notificationPanel::class.java))
                    Toast.makeText(applicationContext, "Clicked Notifications", Toast.LENGTH_SHORT)
                        .show()
                }
                R.id.nav_settings->Toast.makeText(applicationContext,"Clicked Settings",Toast.LENGTH_SHORT).show()
                R.id.nav_near_me->Toast.makeText(applicationContext,"Clicked Near me",Toast.LENGTH_SHORT).show()
                R.id.nav_update->Toast.makeText(applicationContext,"Clicked Update",Toast.LENGTH_SHORT).show()
                R.id.nav_login->startActivity(Intent(this@Home,UserProfile::class.java))
                R.id.nav_logout-> Firebase.auth.signOut().also { startActivity(Intent(this@Home,SignUpActivity::class.java)) }
                R.id.nav_share->Toast.makeText(applicationContext,"Clicked Share",Toast.LENGTH_SHORT).show()
                R.id.nav_rate_us->Toast.makeText(applicationContext,"Clicked Rate us",Toast.LENGTH_SHORT).show()



            }

            true



        }

        donateBlood.setOnClickListener {
            startActivity(Intent(this@Home, blood_donation::class.java))
        }

        rider.setOnClickListener {
            startActivity(Intent(this@Home,rider_home::class.java))
        }
        repair.setOnClickListener {
            startActivity(Intent(this@Home,E_repair::class.java))
        }
        parcel.setOnClickListener{
            startActivity(Intent(this@Home,ParcelDelivery::class.java))
        }
        findtutor.setOnClickListener {
            startActivity(Intent(this@Home,FindTutor::class.java))
        }
        tourguide.setOnClickListener {
            startActivity(Intent(this@Home,TourGuide::class.java))
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}