package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toolbar
import com.example.notes.DBHandler.DatabaseHandler
import com.example.notes.Model.Notes

class Main_edit : AppCompatActivity() {

    lateinit var toolbar:androidx.appcompat.widget.Toolbar
    var _id:Int =0
    lateinit var edtupdateTitle:EditText
    lateinit var edtupdateNotes:EditText
    lateinit var btnupdate:Button
    lateinit var notes:Notes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_edit)

        toolbar =findViewById(R.id.toolbar)
        edtupdateTitle =findViewById(R.id.edt_title)
        edtupdateNotes =findViewById(R.id.edt_note)
        btnupdate =findViewById(R.id.btnAdd)



        setSupportActionBar(toolbar)

        _id = intent.getIntExtra("_id",0)
    }

    override fun onStart() {
        super.onStart()
        var dbHandler =DatabaseHandler(this)
        notes = dbHandler.ReadspecificNotes(_id)
        edtupdateTitle.setText(notes.title)
        edtupdateNotes.setText(notes.note)

        btnupdate.setOnClickListener {
            updateNotes()
        }


    }

     fun updateNotes() {
        if(edtupdateTitle.text.toString() == notes.title &&
                edtupdateNotes.text.toString() == notes.note){
            finish()
        }else{
            var dbHandler = DatabaseHandler(this)

           var rows= dbHandler.updateNotes(
                Notes(_id,
                    edtupdateTitle.text.toString(),
                    edtupdateNotes.text.toString()))

            if(rows > 0){
                Toast.makeText(this, "Update succesfull", Toast.LENGTH_SHORT).show()
                finish()
            }
            }

        }
    }
