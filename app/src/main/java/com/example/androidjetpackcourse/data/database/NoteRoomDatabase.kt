package com.example.androidjetpackcourse.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidjetpackcourse.data.database.entities.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteRoomDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao
}