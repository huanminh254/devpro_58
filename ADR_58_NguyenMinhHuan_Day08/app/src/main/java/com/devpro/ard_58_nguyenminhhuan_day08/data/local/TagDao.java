package com.devpro.ard_58_nguyenminhhuan_day08.data.local;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.devpro.ard_58_nguyenminhhuan_day08.model.Tag;

import java.util.List;

@Dao
public interface TagDao {
    @Insert
    void insert(Tag tag);
    @Update
    void update(Tag tag);
    @Delete
    void delete(Tag tag);
    @Query("SELECT * FROM tags")
    LiveData<List<Tag>> getAllTag();
    @Query("SELECT * FROM tags WHERE id = :id")
    LiveData<Tag> selectTag(int id);
}
