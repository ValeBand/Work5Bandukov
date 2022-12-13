package com.example.mynote.screens.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.mynote.R
import com.example.mynote.databinding.FragmentDetailBinding
import com.example.mynote.db.NoteModel
import com.example.mynote.screens.AppViewModel

class DetailFragment : Fragment() {

    lateinit var binding: FragmentDetailBinding
    lateinit var currentNote: NoteModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        currentNote = arguments?.getSerializable("note") as NoteModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init ()
    }

    private fun init(){
        val viewModel = ViewModelProvider(requireActivity())[AppViewModel::class.java]

        viewModel.selectedNote.observe(viewLifecycleOwner){
            // Здесь получаете блоктот через it
            binding.tvTitleDetail.text = it.title
            binding.descTitleDetail.text = it.description
        }


        binding.btnDelete.setOnClickListener{
            viewModel.delete(currentNote)
            Navigation.findNavController(it).navigate(R.id.action_detailFragment_to_startFragment)
        }

        binding.btnBack.setOnClickListener {
           Navigation.findNavController(it).navigate(R.id.action_detailFragment_to_startFragment)
        }

    }
}

