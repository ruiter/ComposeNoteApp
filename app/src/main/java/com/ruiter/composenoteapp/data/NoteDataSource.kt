package com.ruiter.composenoteapp.data

import com.ruiter.composenoteapp.model.Note

class NoteDataSource {

    fun loadNotes(): List<Note> {
        return listOf(
            Note(title = "test test", description = "test test test"),
            Note(title = "test test", description = "test test test"),
            Note(title = "test test", description = "test test test"),
            Note(title = "test test", description = "test test test"),
            Note(title = "test test", description = "test test test")
        )
    }
}