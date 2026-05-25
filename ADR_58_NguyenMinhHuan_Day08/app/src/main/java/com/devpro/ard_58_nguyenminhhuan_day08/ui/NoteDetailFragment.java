package com.devpro.ard_58_nguyenminhhuan_day08.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.devpro.ard_58_nguyenminhhuan_day08.R;
import com.devpro.ard_58_nguyenminhhuan_day08.adapter.NoteAdapter;
import com.devpro.ard_58_nguyenminhhuan_day08.model.Note;
import com.devpro.ard_58_nguyenminhhuan_day08.ui.viewmodel.ShareViewModel;


public class NoteDetailFragment extends Fragment {
    private TextView tvDetailTitle;
    private TextView tvDetailDate;
    private TextView tvDetailTime;
    private TextView tvDetailTag;
    private TextView tvDetailContent;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ShareViewModel shareViewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);
        Note note = shareViewModel.getQueueNote().getValue();
        if(note!=null){
            tvDetailTitle = view.findViewById(R.id.tv_detail_title);
            tvDetailDate = view.findViewById(R.id.tv_detail_date);
            tvDetailTime = view.findViewById(R.id.tv_detail_time);
            tvDetailTag = view.findViewById(R.id.tv_detail_tag);
            tvDetailContent = view.findViewById(R.id.tv_detail_content);
            tvDetailTitle.setText(note.getNameNode());
            tvDetailDate.setText(String.valueOf(note.getDateNote()));
            tvDetailTime.setText(String.valueOf(note.getTimeNote()));
            tvDetailTag.setText(String.valueOf(note.getIdTagNote()));
            tvDetailContent.setText(note.getContentNote());
        }
        view.findViewById(R.id.btn_back_detail).setOnClickListener(v->{
            getParentFragmentManager().popBackStack();
        });
        view.findViewById(R.id.btn_edit_note).setOnClickListener(v->{
            String title = tvDetailTitle.getText().toString();
            String date = tvDetailDate.getText().toString();
            String time = tvDetailTime.getText().toString();
            String tag = tvDetailTag.getText().toString();
            String content = tvDetailContent.getText().toString();
            if (title.isEmpty()) {
                Toast.makeText(getContext(), "Tiêu đề không được để trống!", Toast.LENGTH_SHORT).show();
                return;
            }
            note.setNameNode(title);
            note.setDateNote(date);
            note.setTimeNote(time);
            note.setIdTagNote(Integer.parseInt(tag));
            note.setContentNote(content);
            shareViewModel.updateNote(note);
            shareViewModel.setQueueNote(note);
            Toast.makeText(getContext(), "Lưu thành công!", Toast.LENGTH_SHORT).show();
            getParentFragmentManager().popBackStack();
        });
    }
}