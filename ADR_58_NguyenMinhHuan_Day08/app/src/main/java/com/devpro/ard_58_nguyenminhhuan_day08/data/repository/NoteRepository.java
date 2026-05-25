package com.devpro.ard_58_nguyenminhhuan_day08.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.devpro.ard_58_nguyenminhhuan_day08.data.local.AppDatabase;
import com.devpro.ard_58_nguyenminhhuan_day08.data.local.NoteDao;
import com.devpro.ard_58_nguyenminhhuan_day08.model.Note;

import java.util.List;

import lombok.Getter;

public class NoteRepository {
    private final NoteDao noteDao;
    @Getter
    private final LiveData<List<Note>> allNotes;
    public NoteRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        noteDao = db.noteDao();
        allNotes = noteDao.getAllNote();
    }
    public void insert(Note note){
        AppDatabase.databaseWriteExecutor.execute(()->{
            noteDao.insert(note);
        });
    }
    public void update(Note note){
        AppDatabase.databaseWriteExecutor.execute(()->{
            noteDao.update(note);
        });
    }
    public void delete(Note note){
        AppDatabase.databaseWriteExecutor.execute(()->{
            noteDao.delete(note);
        });
    }

    public LiveData<Note> selectNote(int id){
        return noteDao.selectNote(id);
    }
}
