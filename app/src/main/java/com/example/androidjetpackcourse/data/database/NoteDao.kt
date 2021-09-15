package com.example.androidjetpackcourse.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.androidjetpackcourse.data.database.entities.Note

@Dao
interface NoteDao {

    @Insert
    fun insert(note: Note)

    @get:Query("SELECT * FROM notes")
    val allNotes: LiveData<List<Note>>

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)
}