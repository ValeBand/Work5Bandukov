package com.example.mynote.screens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.mynote.db.NoteDao
import com.example.mynote.db.NoteDatabase
import com.example.mynote.db.NoteModel
import kotlinx.coroutines.launch

class AppViewModel(application: Application) : AndroidViewModel(application) {
    private val dao: NoteDao
    init {
        dao = Room.databaseBuilder(application.applicationContext, NoteDatabase::class.java, "note_db").build().getNoteDao()
    }

   var  notes: LiveData<List<NoteModel>> = dao.getAllNotes()

    var selectedNote: MutableLiveData<NoteModel> = MutableLiveData()


    fun insert(note: NoteModel){
        viewModelScope.launch {
            dao.insert(note)
        }
    }

    fun delete(currentNote: NoteModel) {
        viewModelScope.launch {
            dao.delete(currentNote)
        }

    }


}