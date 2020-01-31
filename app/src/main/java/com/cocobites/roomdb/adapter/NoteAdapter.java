package com.cocobites.roomdb.adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cocobites.roomdb.R;
import com.cocobites.roomdb.data.model.db.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private List<Note> mDataSet;
    private OnNoteAdapterIListener mListener;

    public NoteAdapter(OnNoteAdapterIListener listener) {
        mDataSet = new ArrayList<>();
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Note data = mDataSet.get(position);

        holder.tvId.setBackgroundColor(data.getBgColorInt());

        holder.tvId.setText(String.valueOf(data.getId()));
        holder.tvTitle.setText(data.getTitle());
        holder.tvNote.setText(data.getContent());

    }

    public void setNotes(List<Note> notes) {
        mDataSet = notes;
        notifyDataSetChanged();
    }
    public void restoreNote(Note note, int pos) {
        mDataSet.set(pos, note);
        notifyItemChanged(pos);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvId, tvTitle, tvNote;
        ImageButton ibDelete;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvNote = itemView.findViewById(R.id.tvNote);
            ibDelete = itemView.findViewById(R.id.ibDelete);

            itemView.setOnClickListener(this);
            ibDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Note note = mDataSet.get(getAdapterPosition());
            if (v.getId() == R.id.ibDelete){
                mListener.onDelete(note, getAdapterPosition());
                mDataSet.remove(getAdapterPosition());
            } else {
                mListener.onClick(note);
            }
        }
    }

    public interface OnNoteAdapterIListener{
        void onClick(Note note);
        void onDelete(Note note, int pos);
    }
}
