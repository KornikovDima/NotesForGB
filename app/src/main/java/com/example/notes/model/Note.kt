package com.example.notes.model

data class Note(
    val id: Long,
    val header: String,
    val description: String
)

data class NoteDetails(
    val note: Note,
    val details: String
)