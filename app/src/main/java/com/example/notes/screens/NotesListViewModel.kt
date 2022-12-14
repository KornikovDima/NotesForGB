package com.example.notes.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes.model.Note
import com.example.notes.model.NotesListener
import com.example.notes.model.NotesService

class NotesListViewModel(
    private val notesService: NotesService
) : ViewModel() {

    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> = _notes

    private val listener: NotesListener = {
        _notes.value = it
    }

    init {
        loadNotes()
    }

    override fun onCleared() {
        super.onCleared()
        notesService.removeListener(listener)
    }

    fun loadNotes() {
        notesService.addListener(listener)
    }

    fun deleteNote(note: Note){
        notesService.deleteNote(note)
    }
}