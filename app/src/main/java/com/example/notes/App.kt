package com.example.notes

import android.app.Application
import com.example.notes.model.NotesService

class App : Application() {

    val notesService = NotesService()
}