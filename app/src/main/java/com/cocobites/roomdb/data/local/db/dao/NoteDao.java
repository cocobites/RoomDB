package com.cocobites.roomdb.data.local.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.cocobites.roomdb.data.model.db.Note;

import java.util.List;

@Dao
public interface NoteDao {

    //@Query("SELECT * from note ORDER BY created_at ASC")
    @Query("SELECT * from note")
    LiveData<List<Note>> getAllNotes();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Note word);

    @Query("SELECT * FROM note WHERE id=:noteId")
    LiveData<Note> getNote(String noteId);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note")
    void deleteAll();
}
