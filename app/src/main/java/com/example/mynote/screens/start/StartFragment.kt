package com.example.mynote.screens.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.mynote.APP
import com.example.mynote.R
import com.example.mynote.adapter.NoteAdapter
import com.example.mynote.databinding.FragmentStartBinding
import com.example.mynote.db.NoteModel
import com.example.mynote.screens.AppViewModel

class StartFragment : Fragment() {

    lateinit var binding: FragmentStartBinding
    lateinit var recyclerView: View
    lateinit var adapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(requireActivity())[AppViewModel::class.java]
        recyclerView = binding.rvNotes
        adapter = NoteAdapter()



        (recyclerView as RecyclerView).adapter = adapter
        viewModel.notes.observe(viewLifecycleOwner) { listNotes ->
            adapter.setList(listNotes.asReversed())
        }


        adapter.listener = {
            viewModel.selectedNote.value = it
            Navigation.findNavController(binding.root).navigate(R.id.action_startFragment_to_detailFragment2)
        }

        binding.btnNext.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_startFragment_to_addNoteFragment)
        }
    }


}