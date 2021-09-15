package com.example.androidjetpackcourse.adapters
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.RecyclerView
import com.example.androidjetpackcourse.R
import com.example.androidjetpackcourse.data.database.entities.Note
import com.example.androidjetpackcourse.view.activities.EditNoteActivity
import kotlinx.android.synthetic.main.note_list_item.view.*

class NoteListAdapter(
    private val mContext: Context,
    private val onDeleteClickListener: OnDeleteClickListener?,
    private val secondActivityWithResult: ActivityResultLauncher<Intent>
) : RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    private var noteList: List<Note> = mutableListOf()

    interface OnDeleteClickListener {
        fun onDeleteClickListener(myNote: Note)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = LayoutInflater.from(mContext).inflate(R.layout.note_list_item, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun getItemCount(): Int = noteList.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = noteList[position]
        holder.setData(note.note, position)
        holder.setListeners()
    }

    fun setNotes(notes: List<Note>) {
        noteList = notes
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var pos: Int = 0

        fun setData(note: String?, position: Int) {
            itemView.txvNote.text = note
            pos = position
        }

        fun setListeners() {
            itemView.setOnClickListener {
                val intent = Intent(mContext, EditNoteActivity::class.java)
                intent.putExtra("note_id", noteList[pos].id)
                intent.putExtra("note", noteList[pos].note)
                secondActivityWithResult.launch(intent)
            }

            itemView.ivDelete.setOnClickListener {
                onDeleteClickListener?.onDeleteClickListener(noteList[pos])
            }
        }
    }
}