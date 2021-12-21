package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toolbar
import com.example.notes.DBHandler.DatabaseHandler
import com.example.notes.Model.Notes
import android.util.Log

class MainActivity2 : AppCompatActivity() {

    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var edt_title:EditText
    lateinit var edt_notes:EditText
    lateinit var btn_add:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_notes)

        toolbar=findViewById(R.id.toolbar)
        edt_title=findViewById(R.id.edt_title)
        edt_notes=findViewById(R.id.edt_note)
        btn_add=findViewById(R.id.btnAdd)

        setSupportActionBar(toolbar)
    }

    override fun onStart() {
        super.onStart()

        btn_add.setOnClickListener {
            addNotes()
        }
    }

    private fun addNotes() {
        var title:String = edt_title.text.toString()
        var note:String = edt_notes.text.toString()

        val dbHanlder = DatabaseHandler(this)
        val rows = dbHanlder.AddNotes(Notes(null,title,note))

        if (rows > -1){
            Toast.makeText(this,"Notes Added Successfully.",Toast.LENGTH_LONG).show()
            Log.d("AddNoteActivity","Rows added $rows")
            finish()
        }else{
            Toast.makeText(this,"Failed to Add Notes!",Toast.LENGTH_LONG).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}