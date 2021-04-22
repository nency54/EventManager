package com.example.luevents

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class db_for_stud(context: Context):SQLiteOpenHelper(context,dbname, null, version) {
    companion object {
        private var dbname = "userDB.db"
        private var version = 1
        private val TABLE_NAME = "credentials"
        private val COLu_ID = "Id"
        private val COL_STUD_ID = "stud_id"
        private val COL_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE =
            ("CREATE TABLE $TABLE_NAME($COLu_ID INTEGER PRIMARY KEY, $COL_STUD_ID TEXT, $COL_PASSWORD TEXT)")
        db!!.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }


    fun insertUserData(stud_id: String, password: String)  {

        val db= this.writableDatabase
        val values = ContentValues()
        values.put(COL_STUD_ID, stud_id)
        values.put(COL_PASSWORD, password)

        db.insert(TABLE_NAME, null, values)
        db.close()

    }

    fun userAuthentication(stud_id: Int, password: String): ArrayList<credentials> {

        //val query= "SELECT * FROM user1 WHERE stud_id= $stud_id AND password= $password"
        val db = this.writableDatabase
        val new = ArrayList<credentials>()
        val query = "select * from TABLE_NAME where stud_id= $stud_id and password = $password"
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val it = credentials()
                it.id1 = cursor.getInt(cursor.getColumnIndex(COLu_ID))
                it.name = cursor.getInt(cursor.getColumnIndex(COL_STUD_ID))
                it.Password = cursor.getString(cursor.getColumnIndex(COL_PASSWORD))
                new.add(it)
            } while (cursor.moveToNext())
        }
        db.close()
        return new
    }

        // if(cursor.moveToFirst()) {
        // do {
        //       if (cursor.count <= 0) {
        //       cursor.close()
        //     return false
        //}

        //}while (cursor.moveToNext())

        //}
        //return true
        //}
    }

