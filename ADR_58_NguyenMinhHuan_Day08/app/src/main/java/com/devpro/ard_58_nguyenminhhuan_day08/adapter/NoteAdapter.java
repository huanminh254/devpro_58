package com.devpro.ard_58_nguyenminhhuan_day08.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devpro.ard_58_nguyenminhhuan_day08.R;
import com.devpro.ard_58_nguyenminhhuan_day08.model.Note;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private List<Note> notes = new ArrayList<>();
    private OnclickItemNote onclickItemNote;

    public interface OnclickItemNote {
        void onClickItemNote(Note note);
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.nameNote.setText(note.getNameNode());
        holder.dateNote.setText(String.valueOf(note.getDateNote()));
        holder.timeNote.setText(String.valueOf(note.getTimeNote()));
        holder.tagNote.setText(String.valueOf(note.getIdTagNote()));
    }

    @Override
    public int getItemCount() {
        return notes != null ? notes.size() : 0;
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView nameNote;
        TextView dateNote;
        TextView timeNote;
        TextView tagNote;
        ImageView imgNote;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            nameNote = itemView.findViewById(R.id.name_note);
            dateNote = itemView.findViewById(R.id.date_note);
            timeNote = itemView.findViewById(R.id.time_note);
            tagNote = itemView.findViewById(R.id.tag_note);
            imgNote = itemView.findViewById(R.id.img_note);
            itemView.setOnClickListener(v -> {
                int position = getBindingAdapterPosition();
                if (position != RecyclerView.NO_POSITION && onclickItemNote != null) {
                    onclickItemNote.onClickItemNote(notes.get(position));
                }
            });
        }
    }
}
