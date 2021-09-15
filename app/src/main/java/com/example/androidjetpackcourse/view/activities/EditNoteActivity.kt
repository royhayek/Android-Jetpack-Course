package com.example.androidjetpackcourse.view.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidjetpackcourse.R
import kotlinx.android.synthetic.main.activity_edit_note.*

// Make sure you add this Activity to the Manifest file
class EditNoteActivity : AppCompatActivity() {

    var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)

        val bundle: Bundle? = intent.extras

        bundle?.let {
            id = bundle.getString("note_id")
            val note = bundle.getString("note")
            etNote.setText(note)
        }

        bSave.setOnClickListener {

            val updatedNote = etNote!!.text.toString()
            val resultIntent = Intent()
            resultIntent.putExtra(NOTE_ID, id)
            resultIntent.putExtra(UPDATED_NOTE, updatedNote)
            setResult(Activity.RESULT_OK, resultIntent)

            finish()
        }

        bCancel.setOnClickListener {
            finish()
        }
    }

    companion object {
        val NOTE_ID = "note_id"
        internal val UPDATED_NOTE = "note_text"
    }
}
