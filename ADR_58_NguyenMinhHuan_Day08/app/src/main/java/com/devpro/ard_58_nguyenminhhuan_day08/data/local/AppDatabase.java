package com.devpro.ard_58_nguyenminhhuan_day08.data.local;


import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.devpro.ard_58_nguyenminhhuan_day08.model.Note;
import com.devpro.ard_58_nguyenminhhuan_day08.model.Tag;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@androidx.room.Database(entities = {Note.class, Tag.class}, version = 5, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TagDao tagDao();
    public abstract NoteDao noteDao();
    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public static AppDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (AppDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                            .fallbackToDestructiveMigration() // Thêm dòng này để tự động cập nhật schema
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
