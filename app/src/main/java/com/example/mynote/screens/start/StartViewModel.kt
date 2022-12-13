package com.example.mynote.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mynote.REPOSITORY
import com.example.mynote.db.NoteDatabase
import com.example.mynote.db.NoteModel
import com.example.mynote.repository.NoteRealization

class StartViewModel(application: Application):AndroidViewModel(application) {

    val context = application

    fun initDatabase(){
        val daoNote = NoteDatabase.getInstance(context).getNoteDao()
        REPOSITORY = NoteRealization(daoNote)
    }

    fun getAllNotices():LiveData<List<NoteModel>> {
        return REPOSITORY.allNotes
    }
}