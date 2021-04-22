package com.example.luevents

import android.content.ComponentCallbacks2
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_adminlogin.*
import java.sql.Types.NULL

class adminlogin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adminlogin)

        admin_login.setOnClickListener {

            if (admin_id.text.toString().equals("Lakehead")&& admin_pass.text.toString().equals("Student")){

            intent = Intent(this, admin_events::class.java)

            startActivity(intent)}
            else{
                Toast.makeText(this,"Wrong ID and Password", Toast.LENGTH_LONG).show()
            }
        }



    }
}
