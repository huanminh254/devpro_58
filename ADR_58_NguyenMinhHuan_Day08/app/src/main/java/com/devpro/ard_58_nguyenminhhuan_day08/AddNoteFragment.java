package com.devpro.ard_58_nguyenminhhuan_day08;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devpro.ard_58_nguyenminhhuan_day08.adapter.TagAdapter;
import com.devpro.ard_58_nguyenminhhuan_day08.model.Note;
import com.devpro.ard_58_nguyenminhhuan_day08.model.Tag;
import com.devpro.ard_58_nguyenminhhuan_day08.ui.viewmodel.ShareViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddNoteFragment extends Fragment {
    private ShareViewModel shareViewModel;
    private TagAdapter tagAdapter;
    private EditText edtTitle, edtContent;
    private int selectedTagId = -1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shareViewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);

        edtTitle = view.findViewById(R.id.edt_note_title);
        edtContent = view.findViewById(R.id.edt_note_content);
        RecyclerView rcvSelectTag = view.findViewById(R.id.rcv_select_tag);

        // Setup Tag RecyclerView
        tagAdapter = new TagAdapter();
        rcvSelectTag.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rcvSelectTag.setAdapter(tagAdapter);

        shareViewModel.getAllTag().observe(getViewLifecycleOwner(), tags -> {
            if (tags != null) {
                tagAdapter.setTags(tags);
            }
        });

        view.findViewById(R.id.btn_back).setOnClickListener(v -> {
            getParentFragmentManager().popBackStack();
        });

        view.findViewById(R.id.btn_save).setOnClickListener(v -> {
            saveNote();
        });
    }

    private void saveNote() {
        String title = edtTitle.getText().toString().trim();
        String content = edtContent.getText().toString().trim();
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String strDate = dateFormat.format(currentDate);
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String strTime = timeFormat.format(currentDate);
        if (TextUtils.isEmpty(title)) {
            Toast.makeText(getContext(), "Please enter title", Toast.LENGTH_SHORT).show();
            return;
        }
        Tag selectedTag = tagAdapter.getSelectedTag();
        int tagId = (selectedTag != null) ? selectedTag.getId() : -1;

        Note note = new Note();
        note.setNameNode(title);
        note.setContentNote(content);
        note.setIdTagNote(tagId);
        note.setDateNote(strDate);
        note.setTimeNote(strTime);
        shareViewModel.insertNote(note);
        Toast.makeText(getContext(), "Note saved successfully", Toast.LENGTH_SHORT).show();
        getParentFragmentManager().popBackStack();
    }
}
