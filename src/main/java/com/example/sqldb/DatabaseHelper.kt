package com.example.sqldb

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import android.support.annotation.RequiresApi

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    companion object { // tying this to a class 
        val DATABASE_NAME = "Student.db"
        val TABLE_NAME = "student_table"
        val COL_1 = "ID"
        val COL_2 = "NAME"
        val COL_3 = "PROFESSION"
        val COL_4 = "SALARY"
    }

    override fun onCreate(db: SQLiteDatabase) { //

        db.execSQL("CREATE TABLE IF NOT EXISTS $TABLE_NAME(ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME  TEXT , PROFESSION TEXT , SALARY INTEGER)")
          //assigning datatypes 
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) { 

        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME") // this drops the table if it exists 
        onCreate(db)

    }

    fun insertData(name: String, profession: String, salary: String): Boolean? {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_2, name)
        cv.put(COL_3, profession)
        cv.put(COL_4, salary)
        val res = db.insert(TABLE_NAME, null, cv)

        return !res.equals("")
    }

    fun getAllData(): Cursor { //Runs the provided SQL and returns a Cursor over the result set//
        val db = this.writableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)// this selects all the data from the table //
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    fun getData(id: String): Cursor {
        val db = this.writableDatabase //opens a database that will be used for reading and writing.//
        return db.rawQuery("SELECT * FROM $TABLE_NAME WHERE ID=? ", arrayOf(id), null) 


    }
// this updates the data and places it into the table//
    fun updateData(id: String, name: String, profession: String, salary: String): Boolean? {
        val db = this.writableDatabase
        val cv = ContentValues() //Creates an empty set of values using the default initial size//
        cv.put(COL_1, id)
        cv.put(COL_2, name)
        cv.put(COL_3, profession)
        cv.put(COL_4, salary)
        db.update(TABLE_NAME, cv, "ID=?", arrayOf(id)) // checks the array of IDs //
        return true
    }

    fun daleteData(id: String): Int? {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME, "ID =? ", arrayOf(id)) // deletes the ID and data from the table//
    }
}
