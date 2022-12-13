package com.example.mynote.screens.addnote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.mynote.APP
import com.example.mynote.R
import com.example.mynote.databinding.FragmentAddNoteBinding
import com.example.mynote.db.NoteModel
import com.example.mynote.screens.AppViewModel

class AddNoteFragment : Fragment() {

    lateinit var binding: FragmentAddNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(requireActivity()).get(AppViewModel::class.java)
        binding.btnAddNote.setOnClickListener{
            val title = binding.etAddTitle.text.toString()
            val description = binding.etAddDesc.text.toString()
            viewModel.insert(NoteModel(title = title, description = description))
           Navigation.findNavController(it).navigate(R.id.action_addNoteFragment_to_startFragment)
        }
        binding.btnBack.setOnClickListener{
           Navigation.findNavController(it).navigate(R.id.action_addNoteFragment_to_startFragment)
        }
    }
}