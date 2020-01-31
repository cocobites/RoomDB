package com.cocobites.roomdb.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.cocobites.roomdb.ui.dialog.AddOrEditDialogFragment;
import com.cocobites.roomdb.ui.decoration.DividerItemDecoration;
import com.cocobites.roomdb.R;
import com.cocobites.roomdb.adapter.NoteAdapter;
import com.cocobites.roomdb.data.model.db.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity
        extends AppCompatActivity
        implements AddOrEditDialogFragment.AddOrEditDialogIListener, NoteAdapter.OnNoteAdapterIListener {

    private MainViewModel mViewModel;

    private NoteAdapter mAdapter;

    private CoordinatorLayout rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        setUpRecyclerView();
        rootView = findViewById(R.id.rootView);
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddOrEditDialog(null);
            }
        });
    }

    private void setUpRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        mAdapter = new NoteAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this));
        recyclerView.setAdapter(mAdapter);

        mViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                mAdapter.setNotes(notes);
            }
        });
    }

    private void showAddOrEditDialog(Note note){
        AddOrEditDialogFragment dialogFragment = AddOrEditDialogFragment.newInstance(note);
        dialogFragment.show(getSupportFragmentManager(), "");
    }

    @Override
    public void onSave(Note note) {
        if (note.getId() != null){
            mViewModel.update(note);
        } else mViewModel.insert(note);
    }

    @Override
    public void onClick(Note note) {
        showAddOrEditDialog(note);
    }

    @Override
    public void onDelete(Note note, int pos) {
        showDeleteSnackBar(note, pos);
    }

    private void showDeleteSnackBar(final Note note, final int pos){
        mViewModel.delete(note);
        Snackbar snackbar = Snackbar
                .make(rootView, "Note is deleted!", Snackbar.LENGTH_LONG)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //mAdapter.restoreNote(note, pos);
                        mViewModel.insert(note);
                        Snackbar snackbar1 = Snackbar.make(rootView, "Note is restored!", Snackbar.LENGTH_SHORT);
                        snackbar1.show();
                    }
                });

        snackbar.show();
    }
}
