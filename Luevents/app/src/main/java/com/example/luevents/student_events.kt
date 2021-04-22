package com.example.luevents

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_student_events.*


internal lateinit var db:database
internal var firstpersons1: List<events> = ArrayList<events>()


class student_events : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_events)
        db= database(this)


        to_home.setOnClickListener{


            intent = Intent(this,MainActivity::class.java)

            startActivity(intent)

        }



                refreshData1()


    }
    private fun refreshData1() {
        firstpersons1= db.myevents
        val adapter= stdadapter(this@student_events,firstpersons1)
        lstdview.adapter= adapter
    }





        }


