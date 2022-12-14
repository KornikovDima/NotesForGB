package com.example.notes.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes.NoteNotFoundException
import com.example.notes.model.NoteDetails
import com.example.notes.model.NotesService

class NoteDetailsViewModel(
    private val notesService: NotesService
) : ViewModel() {

    private val _noteDetails = MutableLiveData<NoteDetails>()
    val noteDetails: LiveData<NoteDetails> = _noteDetails

    fun loadNote(noteId: Long) {
        if (_noteDetails.value != null) return
        try {
            _noteDetails.value = notesService.getById(noteId)
        } catch (e: NoteNotFoundException) {
            e.printStackTrace()
        }

    }

    fun deleteNote() {
        val noteDetails = this.noteDetails.value?: return
        notesService.deleteNote(noteDetails.note)
    }
}