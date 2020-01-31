package com.cocobites.roomdb.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.cocobites.roomdb.data.model.db.Note;
import com.cocobites.roomdb.ui.base.BaseViewModel;

import java.util.List;

public class MainViewModel extends BaseViewModel {

    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    LiveData<List<Note>> getAllNotes() {
        return mDbHelper.getAllNotes();
    }

    void insert(Note note) {
        mDbHelper.insertNote(note);
    }

    void insertAt(Note note, int pos) {
        mDbHelper.insertNote(note);
    }

    void update(Note note) {
        mDbHelper.updateNote(note);
    }

    void delete(Note note) {
        mDbHelper.deleteNote(note);
    }

}
