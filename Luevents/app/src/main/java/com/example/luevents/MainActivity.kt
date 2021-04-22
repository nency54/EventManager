package com.example.luevents

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        admin.setOnClickListener{


           intent= Intent(this, adminlogin::class.java)

            startActivity(intent)


        }
        students.setOnClickListener{

            intent= Intent (this, studentlogin::class.java)

            startActivity(intent)

        }




    }
}


