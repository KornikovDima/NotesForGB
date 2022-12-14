package com.example.notes.model

import com.example.notes.NoteNotFoundException

typealias NotesListener = (notes: List<Note>) -> Unit

class NotesService {

    private var notes = mutableListOf<Note>()

    private val listeners = mutableSetOf<NotesListener>()

    init {
        notes = (1..100).map { Note(
            id = it.toLong(),
            header = "Header $it",
            description = "description $it"
        )}.toMutableList()
    }

    fun genNotes(): List<Note> {
        return notes
    }

    fun getById(id: Long): NoteDetails {
        val note = notes.firstOrNull { it.id == id } ?: throw NoteNotFoundException()
        return NoteDetails(
            note = note,
            details = "Какая-то заметка"
        )
    }

    fun deleteNote(note: Note) {
        notes.remove(note)
        notifyChanges()
    }

    fun addListener(listener: NotesListener) {
        listeners.add(listener)
        listener.invoke(notes)
    }

    fun removeListener(listener: NotesListener) {
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        listeners.forEach { it.invoke(notes) }
    }
}