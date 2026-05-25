package com.devpro.ard_58_nguyenminhhuan_day08.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.devpro.ard_58_nguyenminhhuan_day08.data.repository.NoteRepository;
import com.devpro.ard_58_nguyenminhhuan_day08.data.repository.TagRepository;
import com.devpro.ard_58_nguyenminhhuan_day08.model.Note;
import com.devpro.ard_58_nguyenminhhuan_day08.model.Tag;

import java.util.List;

import lombok.Getter;

public class ShareViewModel extends AndroidViewModel {
    private final TagRepository tagRepository;
    private final NoteRepository noteRepository;
    private final LiveData<List<Tag>> allTags;
    private final LiveData<List<Note>> allNotes;
    @Getter
    private final MutableLiveData<Note> queueNote = new MutableLiveData<>();


    public ShareViewModel(@NonNull Application application) {
        super(application);
        tagRepository = new TagRepository(application);
        noteRepository = new NoteRepository(application);
        allTags = tagRepository.getAllTags();
        allNotes = noteRepository.getAllNotes();
    }
    public void insertTag(Tag tag){
        tagRepository.insert(tag);
    }
    public void updateTag(Tag tag){
        tagRepository.update(tag);
    }
    public void deleteTag(Tag tag){
        tagRepository.delete(tag);
    }
    public LiveData<List<Tag>> getAllTag(){
        return allTags;
    }
    public LiveData<Tag> selectTag(int id){
        return tagRepository.selectTags(id);
    }
    public void insertNote(Note note){
        noteRepository.insert(note);
    }
    public void updateNote(Note note){
        noteRepository.update(note);
    }
    public void deleteNote(Note note){
        noteRepository.delete(note);
    }
    public LiveData<List<Note>> getAllNote(){
        return allNotes;
    }
    public LiveData<Note> selectNote(int id){
        return noteRepository.selectNote(id);
    }
    public void setQueueNote(Note note){
        queueNote.setValue(note);
    }
    public LiveData<Note> getQueueNote(){
        return queueNote;
    }
}
