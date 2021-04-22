package com.example.luevents

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class database(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {

    companion object{
        private val DATABASE_VER=1
        private val DATABASE_NAME= "EVENTS.db"
        private val TABLE_NAME="Events"
        private val COL_ID="Id"
        private val COL_NAME="Name"
        private val COL_LOCATION="Location"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY= ("CREATE TABLE $TABLE_NAME($COL_ID INTEGER PRIMARY KEY, $COL_NAME TEXT, $COL_LOCATION TEXT)")
        db!!.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
       db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }
    //crud

    val myevents: List<events>


    get()
    {
        val firstev = ArrayList<events>()
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val db= this.writableDatabase
        val cursor= db.rawQuery(selectQuery,null)
        if (cursor.moveToFirst()){
            do {
                val evnts= events()
                evnts.id= cursor.getInt(cursor.getColumnIndex(COL_ID))
                evnts.name=cursor.getString(cursor.getColumnIndex(COL_NAME))
                evnts.location= cursor.getString( cursor.getColumnIndex(COL_LOCATION))

                firstev.add(evnts)
            }while (cursor.moveToNext())
        }
        db.close()
        return firstev





    }




    fun addevents(evnts: events)
    {
        val db= this.writableDatabase
        val values= ContentValues()
        values.put(COL_ID, evnts.id)
        values.put(COL_NAME, evnts.name)
        values.put(COL_LOCATION, evnts.location)

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun updateevents(evnts: events): Int
    {
        val db= this.writableDatabase
        val values= ContentValues()
        values.put(COL_ID, evnts.id)
        values.put(COL_NAME, evnts.name)
        values.put(COL_LOCATION, evnts.location)

        return db.update(TABLE_NAME, values,"$COL_ID=?", arrayOf(evnts.id.toString()))

    }

    fun deleteevents(evnts: events)
    {
        val db= this.writableDatabase


        db.delete(TABLE_NAME, "$COL_ID=?", arrayOf(evnts.id.toString()))
        db.close()

    }






}