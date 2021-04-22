package com.example.luevents

import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import kotlinx.android.synthetic.main.row_layout.view.*
import com.google.firebase.auth.FirebaseAuth


class eventsadapter(internal var activity: Activity,
                    internal var firstperson:List<events>,
                    internal var edt_id: EditText,
                    internal var edt_name: EditText,
                    internal var edt_location: EditText) : BaseAdapter() {




    internal var inflater: LayoutInflater


    init {
        inflater= activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE)as LayoutInflater
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?) : View{




        val rowView: View
        rowView=inflater.inflate(R.layout.row_layout,null)


        rowView.txt_row_id.text= firstperson[position].id.toString()
        rowView.txt_name.text= firstperson[position].name.toString()
        rowView.txt_location.text= firstperson[position].location.toString()


        rowView.setOnClickListener{
            edt_id.setText(rowView.txt_row_id.text.toString())
            edt_name.setText(rowView.txt_name.text.toString())
            edt_location.setText(rowView.txt_location.text.toString())

        }


        return rowView
    }
    override fun getItem(position: Int): Any{
        return firstperson[position]
    }

    override fun getItemId(position: Int): Long{
        return firstperson[position].id.toLong()
    }

    override fun getCount(): Int{
        return firstperson.size
    }





}