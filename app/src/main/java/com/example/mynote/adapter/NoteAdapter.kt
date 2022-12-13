package com.example.mynote.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynote.R
import com.example.mynote.databinding.ItemLayoutBinding
import com.example.mynote.db.NoteModel


class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){

    var listNote = emptyList<NoteModel>()

    var listener: ((NoteModel) -> Unit)? = null

    class NoteViewHolder(view: View): RecyclerView.ViewHolder(view){
        var binding = ItemLayoutBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return NoteViewHolder(view)

    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.binding.itemTitle.text = listNote[position].title
        holder.itemView.setOnClickListener {
            listener?.let { it1 -> it1(listNote[position]) }
        }

    }

    override fun getItemCount(): Int {
        return listNote.size

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<NoteModel>){
        listNote = list
        notifyDataSetChanged()
    }

}