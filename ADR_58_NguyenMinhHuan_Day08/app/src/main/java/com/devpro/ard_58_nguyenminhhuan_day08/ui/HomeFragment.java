package com.devpro.ard_58_nguyenminhhuan_day08.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.devpro.ard_58_nguyenminhhuan_day08.AddNoteFragment;
import com.devpro.ard_58_nguyenminhhuan_day08.R;
import com.devpro.ard_58_nguyenminhhuan_day08.adapter.NoteAdapter;
import com.devpro.ard_58_nguyenminhhuan_day08.adapter.TagAdapter;
import com.devpro.ard_58_nguyenminhhuan_day08.model.Note;
import com.devpro.ard_58_nguyenminhhuan_day08.model.Tag;
import com.devpro.ard_58_nguyenminhhuan_day08.ui.viewmodel.ShareViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private ShareViewModel shareViewModel;
    private TagAdapter tagAdapter;
    private NoteAdapter noteAdapter;
    private RecyclerView rcvTag, rcvNotes;
    private List<Note> saveNotes = new ArrayList<>();
    private EditText search;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shareViewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);

        noteAdapter = new NoteAdapter();
        rcvNotes = view.findViewById(R.id.rcv_notes);
        rcvNotes.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rcvNotes.setAdapter(noteAdapter);
        noteAdapter.setOnclickItemNote(note -> {
            shareViewModel.setQueueNote(note);
            NoteDetailFragment noteDetailFragment = new NoteDetailFragment();
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, noteDetailFragment)
                    .addToBackStack(null)
                    .commit();
        });

        rcvTag = view.findViewById(R.id.rcv_tag);
        tagAdapter = new TagAdapter();
        rcvTag.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rcvTag.setAdapter(tagAdapter);
        tagAdapter.setOnItemTagClickListener(tag -> {
            filterNote(saveNotes, true);
        });
        shareViewModel.getAllTag().observe(getViewLifecycleOwner(), tags -> {
            if (tags != null) {
                tagAdapter.setTags(tags);
            }
        });

        shareViewModel.getAllNote().observe(getViewLifecycleOwner(), notes -> {
            if (notes != null) {
                saveNotes = notes;
                filterNote(notes, true);
            }
        });

        view.findViewById(R.id.btn_add_note).setOnClickListener(v -> {
            replaceFragment(new AddNoteFragment());
        });

        view.findViewById(R.id.create_tag).setOnClickListener(v -> {
            replaceFragment(new CreatTagFragment());
        });
        search = view.findViewById(R.id.search_bar);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {}
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterNote(saveNotes, false);
            }
        });
    }
    private void filterNote(List<Note> notes, boolean check){
        if (notes != null) {
            List<Note> filteredList = new ArrayList<>();
            if(check == true){
                Tag selectedTag = tagAdapter.getSelectedTag();
                if(selectedTag != null){
                    for (Note note : notes) {
                        if (note.getIdTagNote() == selectedTag.getId()) {
                            filteredList.add(note);
                        }
                    }
                    noteAdapter.setNotes(filteredList);
                }else{
                    noteAdapter.setNotes(notes);
                }
            }
            else{
                String title = search.getText().toString().trim();
                for(Note note : notes){
                    if(note.getNameNode() != null && note.getNameNode().toLowerCase().contains(title.toLowerCase())){
                        filteredList.add(note);
                    }
                }
                noteAdapter.setNotes(filteredList);
            }
            noteAdapter.notifyDataSetChanged();
        }
    }
    private void replaceFragment(Fragment fragment) {
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
