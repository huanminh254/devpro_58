package com.devpro.ard_58_nguyenminhhuan_day05.model;

public class OptionPinterest {
    private String name;
    private boolean isSelected;
    public OptionPinterest(String name, boolean isSelected) {
        this.name = name;
        this.isSelected = isSelected;
    }
    public String getName() {
        return name;
    }
    public boolean isSelected() {
        return isSelected;
    }
    public void setSelected(boolean selected) {
        isSelected = selected;
    }
    public void setName(String name) {
        this.name = name;
    }
}
