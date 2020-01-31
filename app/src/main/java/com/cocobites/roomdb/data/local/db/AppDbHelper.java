package com.cocobites.roomdb.data.local.db;

import androidx.lifecycle.LiveData;

import com.cocobites.roomdb.data.model.db.Note;

import java.util.List;

public class AppDbHelper implements DbHelper {

    private final AppDatabase mAppDatabase;

    public AppDbHelper(AppDatabase mAppDatabase) {
        this.mAppDatabase = mAppDatabase;
    }


    @Override
    public LiveData<List<Note>> getAllNotes() {
        return mAppDatabase.noteDao().getAllNotes();
    }

    @Override
    public void insertNote(final Note note) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mAppDatabase.noteDao().insert(note);
            }
        });
    }

    @Override
    public void updateNote(final Note note) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mAppDatabase.noteDao().update(note);
            }
        });
    }

    @Override
    public void deleteNote(final Note note) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mAppDatabase.noteDao().delete(note);
            }
        });
    }
}
