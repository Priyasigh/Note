package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.DBHandler.DatabaseHandler
import com.example.notes.Model.Notes
import com.example.notes.recycleradapter.notesadapter
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    lateinit var toolbar:androidx.appcompat.widget.Toolbar
    lateinit var recycleView:RecyclerView
    lateinit var fab:FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar=findViewById(R.id.toolbar)
        recycleView=findViewById(R.id.recyclerView)
        fab=findViewById(R.id.fab)
        setSupportActionBar(toolbar)
    }

    override fun onStart() {
        super.onStart()
        fab.setOnClickListener {
            var intent=Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }
        viewnotes()
    }

    private fun viewnotes() {
        val dbHandler = DatabaseHandler(this)
        val noteList:ArrayList<Notes> = dbHandler.ReadNotes()

        recycleView.adapter=notesadapter(this,noteList)
    }

    override fun onResume() {
        super.onResume()
        viewnotes()
    }
    override fun onBackPressed() {
        super.onBackPressed()
    }
}