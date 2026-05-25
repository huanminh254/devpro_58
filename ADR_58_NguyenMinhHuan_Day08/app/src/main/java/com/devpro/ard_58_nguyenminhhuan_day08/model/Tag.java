package com.devpro.ard_58_nguyenminhhuan_day08.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity(tableName = "tags")
public class Tag {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private boolean isSelected;

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
        this.isSelected = false;
    }
}
