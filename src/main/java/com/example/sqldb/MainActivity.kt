package com.example.sqldb

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

 // initializing the buttons and referencing them after //
    lateinit var myAddbtn: Button
    lateinit var myUpdatebtn: Button
    lateinit var myDeletebtn: Button
    lateinit var myVAbtn: Button
   

    lateinit var myid: EditText
    lateinit var myName: EditText
    lateinit var myProfession: EditText
    lateinit var mySalary: EditText

    lateinit var myDb: DatabaseHelper

 //assigns the ids to the button//
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myAddbtn = findViewById(R.id.myAddbtn)
        myVAbtn = findViewById(R.id.myVAbtn)
        myDeletebtn = findViewById(R.id.myDeletebtn)
        myUpdatebtn = findViewById(R.id.myUpdatebtn)

        myid = findViewById(R.id.myid)
        mySalary = findViewById(R.id.mySalary)
        myName = findViewById(R.id.myName)
        myProfession = findViewById(R.id.myProfession)

        myDb = DatabaseHelper(this)

        addData()
        updateData()
        deleteData()
        viewAllData()


    }


// this allows the buttons to be assigned to methods in order for the specific buttons to function.//
 
 // this method allows you to add the data in the table//
    fun addData() {

        myAddbtn.setOnClickListener(View.OnClickListener {

            val name = myName.text.toString().trim()
            val profession = myProfession.text.toString().trim()
            val salary = mySalary.text.toString().trim()


            if (TextUtils.isEmpty(name)) {   //TextUtils allows for the strings to fit inside the given width. If the data is not entered, it will show an error//
                myName.error = "Enter name"
                return@OnClickListener
            }

            if (TextUtils.isEmpty(profession)) {
                myProfession.error = "Enter profession"
                return@OnClickListener
            }
            if (TextUtils.isEmpty(salary)) {
                mySalary.error = "Enter salary"
                return@OnClickListener
            }
           // this will return if all the data is entered to say it has been added otherwise it will say its not inserted//
            val isInserted = myDb.insertData(name, profession, salary)

            if (isInserted == true) {
                Toast.makeText(applicationContext, "Data inserted ", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Data could not be inserted ", Toast.LENGTH_SHORT).show()

            }
        })

    }


// this allows you to see the table full of users and their data entered, if there is no data in the table it will return an error//
    fun viewAllData() {

        myVAbtn.setOnClickListener(View.OnClickListener {
            val res = myDb.getAllData()

            if (res.getCount() == 0) {//sees if their is any users stored on the table//
                showMessage("Error ", "Nothing found") //error if the table has no users//
                return@OnClickListener

            } else {
                val buffer = StringBuffer() //This string represents fixed-length, immutable character sequences //
                while (res.moveToNext()) {
                    buffer.append("Id:" + res.getString(0) + "\n")
                    buffer.append("Name: " + res.getString(1) + "\n\n")
                    buffer.append("Profession: " + res.getString(2) + "\n\n")
                    buffer.append("Salary: " + res.getString(3) + "\n\n")

                }

                showMessage("Data", buffer.toString()) // this shows the message at the top of the table//
            }
        })


    }
// this deletes the content in the table using the id.//
    fun deleteData(){
        myDeletebtn.setOnClickListener(View.OnClickListener {
            val id= myid.getText().toString().trim()
            if(TextUtils.isEmpty(id)){
                myName.error="Enter id"
            }
            val rowdeletion= myDb.daleteData(id)
            if(rowdeletion!!>0){
                Toast.makeText(applicationContext,"Data deleted",Toast.LENGTH_SHORT).show()

            }else {
                Toast.makeText(applicationContext,"Data could not be deleted",Toast.LENGTH_SHORT).show()

            }

        })
    }
// this updates the table after adding the user.//
    fun updateData() {

        myUpdatebtn.setOnClickListener(View.OnClickListener {
            val id = myid.getText().toString().trim()
            val name = myName.text.toString().trim()
            val profession = myProfession.text.toString().trim()
            val salary = mySalary.text.toString().trim()

            // shows the error in a given width//
         if (TextUtils.isEmpty(id)) {
                myid.error = "Enter id"
                return@OnClickListener
            }

            if (TextUtils.isEmpty(name)) {
                myName.error = "Enter name"
                return@OnClickListener
            }

            if (TextUtils.isEmpty(profession)) {
                myProfession.error = "Enter profession"
                return@OnClickListener
            }
            if (TextUtils.isEmpty(salary)) {
                mySalary.error = "Enter salary"
                return@OnClickListener
            }

            val isUpdated = myDb.updateData(id, name, profession, salary)
            if (isUpdated == true) {
                Toast.makeText(applicationContext, "Data updated ", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Data could not be updated ", Toast.LENGTH_SHORT).show()
            }

        })
    }


//
    private fun showMessage(title: String, message: String?) {
//this alert shows up when the user has not entered their data.//
        val builder = AlertDialog.Builder(this)
        builder.create()
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.show()
    }
}







