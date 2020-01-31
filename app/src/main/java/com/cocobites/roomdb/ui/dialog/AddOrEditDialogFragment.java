package com.cocobites.roomdb.ui.dialog;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.cocobites.roomdb.R;
import com.cocobites.roomdb.data.model.db.Note;
import com.cocobites.roomdb.widgets.TextInputEditText;

import java.util.Random;

public class AddOrEditDialogFragment
        extends DialogFragment
        implements View.OnClickListener {

    private static final String TAG = AddOrEditDialogFragment.class.getSimpleName();
    public static final String ARG_NOTE = "note";

    private TextInputEditText etTitle, etNote;

    private Note mNote;

    private AddOrEditDialogIListener mCallback;

    public AddOrEditDialogFragment() {
        // Required empty public constructor
    }


    public static AddOrEditDialogFragment newInstance(Note note) {
        AddOrEditDialogFragment fragment = new AddOrEditDialogFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTE, note);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();

        /*Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(width, height);
        }*/
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddOrEditDialogIListener) {
            mCallback = (AddOrEditDialogIListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement AddOrEditDialogIListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogThemeScale);
        if (getArguments() != null){
            mNote = getArguments().getParcelable(ARG_NOTE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootVIew = inflater.inflate(R.layout.fragment_add_or_edit_dialog, container, false);
        initViews(rootVIew);
        return rootVIew;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (mNote != null){
            etTitle.setText(mNote.getTitle());
            etNote.setText(mNote.getContent());
        }

    }

    private void initViews(View rootView) {
        etTitle = rootView.findViewById(R.id.etTitle);
        etNote = rootView.findViewById(R.id.etNote);
        /*btnSave = */rootView.findViewById(R.id.btnSave).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String title = etTitle.getText().toString();
        String note = etNote.getText().toString();

        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(note)){
            Toast.makeText(getContext(), "Please enter all fields!!", Toast.LENGTH_SHORT).show();
            return;
        }

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));


        if (mNote != null){
            mNote.setTitle(title);
            mNote.setContent(note);
        } else mNote = new Note(title, note, color);

        mCallback.onSave(mNote);
        dismiss();
    }

    public interface AddOrEditDialogIListener{
        void onSave(Note note);
    }

}
