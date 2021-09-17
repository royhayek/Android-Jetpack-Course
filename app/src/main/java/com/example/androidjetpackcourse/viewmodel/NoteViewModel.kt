package com.example.androidjetpackcourse.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidjetpackcourse.data.database.NoteDao
import com.example.androidjetpackcourse.data.database.entities.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteViewModel(private val noteDao: NoteDao) : ViewModel() {

    internal val allNotes: LiveData<List<Note>> = noteDao.allNotes

    fun insert(note: Note) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) { // to run code in Background Thread
                noteDao.insert(note)
            }
        }
    }

    fun update(note: Note) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) { // to run code in Background Thread
                noteDao.update(note)
            }
        }
    }

    fun delete(note: Note) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) { // to run code in Background Thread
                noteDao.delete(note)
            }
        }
    }
}