package com.devpro.ard_58_nguyenminhhuan_day08.ui;

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

import com.devpro.ard_58_nguyenminhhuan_day08.R;
import com.devpro.ard_58_nguyenminhhuan_day08.model.Tag;
import com.devpro.ard_58_nguyenminhhuan_day08.ui.viewmodel.ShareViewModel;


public class CreatTagFragment extends Fragment {
    private ShareViewModel shareViewModel;
    private EditText edtTagName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_creat_tag, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        shareViewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);
        edtTagName = view.findViewById(R.id.edt_tag_name);

        view.findViewById(R.id.btn_back_tag).setOnClickListener(v -> {
            getParentFragmentManager().popBackStack();
        });
        view.findViewById(R.id.btn_save_tag).setOnClickListener(v -> {
            saveTag();
        });
    }

    private void saveTag() {
        String name = edtTagName.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getContext(), "Please enter tag name", Toast.LENGTH_SHORT).show();
            return;
        }

        Tag tag = new Tag(name);
        shareViewModel.insertTag(tag);
        Toast.makeText(getContext(), "Tag saved", Toast.LENGTH_SHORT).show();
        getParentFragmentManager().popBackStack();
    }
}