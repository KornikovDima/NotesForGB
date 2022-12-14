package com.example.notes.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.NotesActionListener
import com.example.notes.NotesAdapter
import com.example.notes.databinding.FragmentNotesListBinding
import com.example.notes.model.Note

class NotesListFragment : Fragment() {

    private lateinit var binding: FragmentNotesListBinding
    private lateinit var adapter: NotesAdapter

    private val viewModel: NotesListViewModel by viewModels{ factory() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesListBinding.inflate(inflater, container, false)
        adapter = NotesAdapter(object : NotesActionListener {
            override fun onNoteDelete(note: Note) {
                viewModel.deleteNote(note)
            }
            override fun onNoteDetails(note: Note) {
                navigator().showDetails(note)
            }
        })

        viewModel.notes.observe(viewLifecycleOwner, Observer {
            adapter.notes = it
        })

        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        return binding.root
    }
}