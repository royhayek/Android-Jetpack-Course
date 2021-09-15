package com.example.androidjetpackcourse.view.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidjetpackcourse.R
import com.example.androidjetpackcourse.adapters.NoteListAdapter
import com.example.androidjetpackcourse.data.database.entities.Note
import com.example.androidjetpackcourse.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_room.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*


class RoomActivity : AppCompatActivity(), NoteListAdapter.OnDeleteClickListener {

    private lateinit var noteViewModel: NoteViewModel

       private val secondActivityWithResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

        println(result.data?.extras)

        if (result.data?.hasExtra(NewNoteActivity.NOTE_ADDED) == true && result.resultCode == Activity.RESULT_OK){

            // Code to insert note
            val noteId = UUID.randomUUID().toString()
            val note = Note(noteId, result.data!!.getStringExtra(NewNoteActivity.NOTE_ADDED))
            noteViewModel.insert(note)

            Toast.makeText(
                applicationContext,
                R.string.saved,
                Toast.LENGTH_LONG
            ).show()

        }else if (result.data?.hasExtra(EditNoteActivity.UPDATED_NOTE) == true && result.resultCode == Activity.RESULT_OK) {

            // Code to update the note
            val note = Note(
                result.data?.getStringExtra(EditNoteActivity.NOTE_ID)!!,
                result.data?.getStringExtra(EditNoteActivity.UPDATED_NOTE)
            )
            noteViewModel.update(note)

            Toast.makeText(
                applicationContext,
                R.string.updated,
                Toast.LENGTH_LONG
            ).show()

        } else {
            Toast.makeText(
                applicationContext,
                R.string.not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        setSupportActionBar(toolbar)

        fab?.setOnClickListener {
            val intent = Intent(this, NewNoteActivity::class.java)
            secondActivityWithResult.launch(intent)
        }

        recyclerview.layoutManager = LinearLayoutManager(this)

        val noteListAdapter = NoteListAdapter(this@RoomActivity, this@RoomActivity, secondActivityWithResult)


        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        noteViewModel.allNotes.observe(this,  { notes ->
            notes?.let {
                noteListAdapter.setNotes(it)
            }
        })

        recyclerview.adapter = noteListAdapter
    }

    override fun onDeleteClickListener(myNote: Note) {
        // Code for delete
        noteViewModel.delete(myNote)
    }
}

