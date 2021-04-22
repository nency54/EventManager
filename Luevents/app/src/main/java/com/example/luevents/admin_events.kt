package com.example.luevents

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_admin_events.*

class admin_events : AppCompatActivity() {
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    val channelId="com.example.luevents"
    val channelId2="com.example.luevents"
    var description="My Notification"

    internal lateinit var db:database
    internal var firstpersons: List<events> = ArrayList<events>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_events)
        val add= findViewById<Button>(R.id.add)

        notificationManager=getSystemService((Context.NOTIFICATION_SERVICE))as NotificationManager



        tomain.setOnClickListener {

            intent= Intent(this, MainActivity::class.java)

            startActivity(intent)
        }


        db= database(this)

        refreshData()


        add.setOnClickListener {

            val event = events(
                Integer.parseInt(edt_id.text.toString()),
                edt_name.text.toString(),
                edt_location.text.toString()

            )


            val builder1= AlertDialog.Builder(this@admin_events)
            builder1.setTitle("Do you want to add this event")
            builder1.setPositiveButton("Yes"){
                dialog, which ->  Toast.makeText(applicationContext,"Event Has Been Added", Toast.LENGTH_LONG).show()

                val intent=Intent(applicationContext, studentlogin::class.java)
                val pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    notificationChannel= NotificationChannel(channelId,description,NotificationManager.IMPORTANCE_HIGH)
                    notificationChannel.enableLights(true)
                    notificationChannel.lightColor=Color.BLUE
                    notificationChannel.enableVibration(true)
                    notificationManager.createNotificationChannel(notificationChannel)

                    builder=Notification.Builder(this, channelId)
                        .setContentTitle("New Event Has Been Added")
                        .setContentText("Click here to Login and Check it out")
                        .setSmallIcon(R.drawable.logo)
                        .setContentIntent(pendingIntent)

                }
                else{
                    builder=Notification.Builder(this)
                        .setContentTitle("New Event Has Been Added")
                        .setContentText("Click here to Login and Check it out")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentIntent(pendingIntent)

                }
                notificationManager.notify(0,builder.build())
                db.addevents(event)
                refreshData()
            }

            builder1.setNegativeButton("No"){
                _,_-> Toast.makeText(applicationContext, "No event was added", Toast.LENGTH_LONG).show()

            }

            builder1.setNeutralButton("Cancel"){
                _,_ -> Toast.makeText(applicationContext,"Pressed Cancel", Toast.LENGTH_LONG).show()
            }


            val dialog1:AlertDialog= builder1.create()

            dialog1.show()



        }
        update.setOnClickListener {
            val event = events(
                Integer.parseInt(edt_id.text.toString()),
                edt_name.text.toString(),
                edt_location.text.toString()

            )
            val intent=Intent(applicationContext, studentlogin::class.java)
            val pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel= NotificationChannel(channelId2,description,NotificationManager.IMPORTANCE_LOW)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor=Color.BLUE
                notificationChannel.enableVibration(true)
                notificationManager.createNotificationChannel(notificationChannel)


                builder=Notification.Builder(this, channelId2)
                    .setContentTitle("An Event Has Been Updated")
                    .setContentText("Click here to Login and Check it out")
                    .setSmallIcon(R.drawable.logo)
                    .setContentIntent(pendingIntent)

            }
            else{
                builder=Notification.Builder(this)
                    .setContentTitle("An Event Has Been Updated")
                    .setContentText("Click here to Login and Check it out")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(pendingIntent)

            }
            notificationManager.notify(0,builder.build())
            db.updateevents(event)
            refreshData()
        }
        delete.setOnClickListener {
            val event = events(
                Integer.parseInt(edt_id.text.toString()),
                edt_name.text.toString(),
                edt_location.text.toString()

            )
            db.deleteevents(event)
            refreshData()
        }


    }

    private fun refreshData() {
        firstpersons= db.myevents
        val adapter= eventsadapter(this@admin_events,firstpersons,edt_id, edt_name, edt_location)
        listperson.adapter= adapter
    }
}
