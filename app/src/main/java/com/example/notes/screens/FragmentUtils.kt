package com.example.notes.screens

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notes.App
import com.example.notes.Navigator

class ViewModelFactory(
    private val app: App
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when(modelClass) {
            NotesListViewModel::class.java -> {
                NotesListViewModel(app.notesService)
            }
            NoteDetailsViewModel::class.java -> {
                NoteDetailsViewModel(app.notesService)
            }
            else -> {
                throw IllegalStateException("Error")
            }
        }
        return viewModel as T
    }
}

fun Fragment.factory() = ViewModelFactory(requireContext().applicationContext as App)

fun Fragment.navigator() = requireActivity() as Navigator