package com.example.notes.DBHandler

import android.content.ContentValues
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.notes.Model.Notes

class DatabaseHandler(context:Context) :
    SQLiteOpenHelper(context, DBNAME,null, DBVERSION) {

    companion object {
        private const val DBNAME = "notesDB"
        private const val DBVERSION = 1
        private const val TABLE_NOTES = "notes_tbl"

        //column for tables
        private const val NOTES_ID = "_id"
        private const val NOTES_TITLE = "notes_title"
        private const val NOTES_TEXT = "notes_text"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_NOTES = "CREATE TABLE $TABLE_NOTES" +
                "($NOTES_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "$NOTES_TITLE TEXT," +
                "$NOTES_TEXT TEXT)"
        db!!.execSQL(CREATE_TABLE_NOTES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NOTES")
        onCreate(db)
    }

    fun AddNotes(notes: Notes): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(NOTES_TITLE, notes.title)
        contentValues.put(NOTES_TEXT, notes.note)

        val response = db.insert(TABLE_NOTES, null, contentValues)
        db.close()
        return response
    }


    fun ReadNotes(): ArrayList<Notes> {

        val db = this.readableDatabase

        val notesCursor = db.rawQuery("SELECT * FROM $TABLE_NOTES", null)
        val notesList: ArrayList<Notes> = ArrayList()

        if (notesCursor.moveToFirst()) {
            do {
                notesList.add(
                    Notes(
                        notesCursor.getInt(0),
                        notesCursor.getString(1),
                        notesCursor.getString(2)
                    )
                )
            } while (notesCursor.moveToNext())
        }
        return notesList
    }

    fun ReadspecificNotes(id:Int): Notes {

        val db = this.readableDatabase

        val notesCursor = db.rawQuery("SELECT * FROM $TABLE_NOTES WHERE $NOTES_ID=$id", null)

        notesCursor.moveToFirst()

        val notesList: Notes=Notes(
                        notesCursor.getInt(0),
                        notesCursor.getString(1),
                        notesCursor.getString(2)
                    )

        db.close()
        return notesList
    }

    fun deleteNotes(id:Int):Int{

        var db = this.writableDatabase

        var rows = db.delete(TABLE_NOTES,"$NOTES_ID=?", arrayOf<String>(id.toString()))
        return rows
    }

    fun updateNotes(notes: Notes):Int {
        var db = this.writableDatabase


        var contentValues=ContentValues()
        contentValues.put(NOTES_TITLE,notes.title)
        contentValues.put(NOTES_TEXT,notes.note)


       var rows= db.update(TABLE_NOTES, contentValues,"$NOTES_ID=?", arrayOf<String>(notes._id.toString()))

        return rows
    }
}