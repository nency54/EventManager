package com.example.luevents

import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.frst.view.*

class stdadapter(internal var activity: Activity,
                 internal var firstperson:List<events>): BaseAdapter()
{
    internal var inflater: LayoutInflater

    init {
        inflater= activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {



        val rowView: View
        rowView=inflater.inflate(R.layout.frst,null)
        rowView.txt_row_id1.text= firstperson[position].id.toString()
        rowView.txt_name1.text= firstperson[position].name.toString()
        rowView.txt_location1.text= firstperson[position].location.toString()


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


