package com.example.notes

import com.example.notes.model.Note

interface Navigator {

    fun showDetails(note: Note)

    fun goBack()

    fun toast(messageRes: Int)

}