
package com.cocobites.roomdb.data.local.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.cocobites.roomdb.data.local.db.dao.NoteDao;
import com.cocobites.roomdb.data.model.db.Note;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Note.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract NoteDao noteDao();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "database")
                            /*.addCallback(sRoomDatabaseCallback)*/
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
