package com.devpro.ard_58_nguyenminhhuan_day08.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.devpro.ard_58_nguyenminhhuan_day08.data.local.AppDatabase;
import com.devpro.ard_58_nguyenminhhuan_day08.data.local.TagDao;
import com.devpro.ard_58_nguyenminhhuan_day08.model.Tag;

import java.util.List;

import lombok.Getter;

public class TagRepository {
    private final TagDao tagDao;
    @Getter
    private final LiveData<List<Tag>> allTags;
    public TagRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        tagDao = db.tagDao();
        allTags = tagDao.getAllTag();
    }
    public void insert(Tag tag){
        AppDatabase.databaseWriteExecutor.execute(()->{
            tagDao.insert(tag);
        });
    }

    public void update(Tag tag){
        AppDatabase.databaseWriteExecutor.execute(()->{
            tagDao.update(tag);
        });
    }
    public void delete(Tag tag){
        AppDatabase.databaseWriteExecutor.execute(()->{
            tagDao.delete(tag);
        });
    }

    public LiveData<Tag> selectTags(int id){
        return tagDao.selectTag(id);
    }
}
