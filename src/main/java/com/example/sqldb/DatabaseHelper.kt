package com.example.sqldb

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import android.support.annotation.RequiresApi

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    companion object {
        val DATABASE_NAME = "Student.db"
        val TABLE_NAME = "student_table"
        val COL_1 = "ID"
        val COL_2 = "NAME"
        val COL_3 = "PROFESSION"
        val COL_4 = "SALARY"
    }

    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL("CREATE TABLE IF NOT EXISTS $TABLE_NAME(ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME  TEXT , PROFESSION TEXT , SALARY INTEGER)")

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
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

    fun getAllData(): Cursor {
        val db = this.writableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    fun getData(id: String): Cursor {
        val db = this.writableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME WHERE ID=? ", arrayOf(id), null)


    }

    fun updateData(id: String, name: String, profession: String, salary: String): Boolean? {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_1, id)
        cv.put(COL_2, name)
        cv.put(COL_3, profession)
        cv.put(COL_4, salary)
        db.update(TABLE_NAME, cv, "ID=?", arrayOf(id))
        return true
    }

    fun daleteData(id: String): Int? {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME, "ID =? ", arrayOf(id))
    }
}