package com.cocobites.roomdb.ui.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.cocobites.roomdb.data.local.db.AppDatabase;
import com.cocobites.roomdb.data.local.db.AppDbHelper;

public class BaseViewModel extends AndroidViewModel {

    protected AppDbHelper mDbHelper;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        AppDatabase db = AppDatabase.getDatabase(application);
        mDbHelper = new AppDbHelper(db);
    }
}
