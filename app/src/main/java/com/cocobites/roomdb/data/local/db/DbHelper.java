package com.cocobites.roomdb.data.local.db;

import androidx.lifecycle.LiveData;

import com.cocobites.roomdb.data.model.db.Note;

import java.util.List;

public interface DbHelper {

    LiveData<List<Note>> getAllNotes();

    void insertNote(final Note note);

    void updateNote(final Note note);

    void deleteNote(final Note note);
}
