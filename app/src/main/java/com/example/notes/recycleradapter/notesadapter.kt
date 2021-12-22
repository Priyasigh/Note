package com.example.notes.recycleradapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.DBHandler.DatabaseHandler
import com.example.notes.Main_edit
import com.example.notes.Model.Notes
import com.example.notes.R

class notesadapter (val context: Context,val notesList: ArrayList<Notes>):
    RecyclerView.Adapter<notesadapter.ViewHolder>() {

    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview){
        var txtTitle:TextView = itemview.findViewById(R.id.txtNotesTitle)
        var txtNotes:TextView = itemview.findViewById(R.id.txtNotesText)
        var parentlayout:LinearLayout = itemview.findViewById(R.id.parentlayout)
        var imgdelete: ImageView = itemview.findViewById((R.id.imgdelete))

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesadapter.ViewHolder {
        val inflate = LayoutInflater.from(context).inflate(R.layout.notes_item,parent,false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: notesadapter.ViewHolder, position: Int) {
        holder.txtTitle.text = notesList[position].title
        holder.txtNotes.text = notesList[position].note

        holder.imgdelete.setOnClickListener{
            var dbHandler = DatabaseHandler(context)

           var rows = dbHandler.deleteNotes(notesList[position]._id!!)

            if (rows > 0){
                Toast.makeText(context,"Note Deleted!", Toast.LENGTH_LONG).show()
                notesList.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position,notesList.size)
            }
        }

        holder.parentlayout.setOnClickListener{
            var intent:Intent =Intent(context,Main_edit::class.java)
            intent.putExtra("_id" ,notesList[position]._id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }


}