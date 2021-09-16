package com.example.androidjetpackcourse.viewmodel

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.androidjetpackcourse.data.database.NoteDao
import com.example.androidjetpackcourse.data.database.NoteRoomDatabase
import com.example.androidjetpackcourse.data.database.entities.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val noteDao: NoteDao
    internal val allNotes: LiveData<List<Note>>

    init {
        val noteDB = NoteRoomDatabase.getDatabase(application)
        noteDao = noteDB!!.noteDao()
        allNotes = noteDao.allNotes
    }

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

//
//    private fun execute() = viewModelScope.launch {
//        doInBackground() // runs in background thread without blocking the Main Thread
//    }
//
//    private suspend fun doInBackground() {
//        withContext(Dispatchers.IO) { // to run code in Background Thread
//            mNoteDao.insert(note)
//            return@withContext "SomeResult"
//        }
//    }

//    private fun <R> CoroutineScope.executeAsyncTask(
//        doInBackground: () -> R
//    ) = launch {
//        withContext(Dispatchers.IO) { // runs in background thread without blocking the Main Thread
//            doInBackground()
//        }
//    }
//
//    private fun insertAsyncTask(mNoteDao: NoteDao, note: Note) {
//        viewModelScope.executeAsyncTask(
//        doInBackground = {
//            mNoteDao.insert(note)
//        })
//    }
//
//    private fun updateAsyncTask(mNoteDao: NoteDao, note: Note) {
//        viewModelScope.executeAsyncTask(
//            doInBackground = {
//                mNoteDao.update(note)
//            })
//    }
//
//
//    private fun deleteAsyncTask(mNoteDao: NoteDao, note: Note) {
//        viewModelScope.executeAsyncTask(
//            doInBackground = {
//                mNoteDao.delete(note)
//            })
//    }
}