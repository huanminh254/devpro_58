package com.devpro.ard_58_nguyenminhhuan_day08.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity(tableName = "notes")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nameNode;
    private String dateNote;
    private String timeNote;
    private int idTagNote;
    private String contentNote;
}
