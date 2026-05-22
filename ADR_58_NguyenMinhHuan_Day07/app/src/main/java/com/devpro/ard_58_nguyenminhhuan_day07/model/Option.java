package com.devpro.ard_58_nguyenminhhuan_day07.model;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Option {
    private String name;
    private boolean isSelected;
    public Option(String name, boolean isSelected) {
        this.name = name;
        this.isSelected = isSelected;
    }
}
