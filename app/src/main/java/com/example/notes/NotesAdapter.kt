package com.example.notes

import android.system.Os.remove
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.ItemNoteBinding
import com.example.notes.model.Note

interface NotesActionListener {

    fun onNoteDelete(note: Note)

    fun onNoteDetails(note: Note)

}

class NotesAdapter(
    private val actionListener: NotesActionListener
) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>(), View.OnClickListener {

    var notes: List<Note> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onClick(v: View?) {
        val note: Note = v?.tag as Note
        when (v.id) {
            R.id.moreButton -> {
                showPopupMenu(v)
            }
            else -> {
                actionListener.onNoteDetails(note)
            }
        }
    }

    override fun getItemCount(): Int = notes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNoteBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)
        binding.moreButton.setOnClickListener(this)

        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = notes[position]
        with(holder.binding) {
            holder.itemView.tag = note
            moreButton.tag = note
            headerTV.text = note.header
        }
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(view.context, view)
        val context = view.context
        val note = view.tag as Note

        popupMenu.menu.add(0, ID_REMOVE, Menu.NONE, context.getString(R.string.popupDelete))

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                ID_REMOVE -> {
                    actionListener.onNoteDelete(note)
                }
            }
            return@setOnMenuItemClickListener true
        }

        popupMenu.show()
    }

    class NotesViewHolder(
        val binding: ItemNoteBinding
    ) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private const val ID_REMOVE = 1
    }
}