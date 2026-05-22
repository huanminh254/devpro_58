package com.devpro.ard_58_nguyenminhhuan_day07.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item_Ingredient {
    private int image;
    private String name;
    private String quantity;
    public Item_Ingredient(int image, String name, String quantity) {
        this.image = image;
        this.name = name;
        this.quantity = quantity;
    }
}
