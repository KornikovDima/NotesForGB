package com.example.notes.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.notes.R
import com.example.notes.databinding.FragmentNotesDetailsBinding

class NoteDetailsFragment : Fragment() {

    private lateinit var binding: FragmentNotesDetailsBinding
    private val viewModel: NoteDetailsViewModel by viewModels { factory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadNote(requireArguments().getLong(ARG_NOTE_ID))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesDetailsBinding.inflate(layoutInflater, container, false)

        viewModel.noteDetails.observe(viewLifecycleOwner, Observer {
            binding.headerTVDetails.text = it.note.header
            binding.descriptionTVDetails.text = it.details
        })

        binding.saveNote.setOnClickListener {
            viewModel.deleteNote()
            navigator().toast(R.string.note_deleted)
            navigator().goBack()
        }

        return binding.root
    }

    companion object{

        private const val ARG_NOTE_ID = "ARG_NOTE_ID"

        fun newInstance(noteID: Long): NoteDetailsFragment {
            val fragment = NoteDetailsFragment()
            fragment.arguments = bundleOf(ARG_NOTE_ID to noteID)
            return fragment
        }
    }
}