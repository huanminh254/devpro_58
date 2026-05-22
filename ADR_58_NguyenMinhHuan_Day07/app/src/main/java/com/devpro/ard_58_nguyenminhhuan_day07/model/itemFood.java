package com.devpro.ard_58_nguyenminhhuan_day07.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class itemFood {
    private int image;
    private String name;
    private boolean isFavourite;
    private String timeFinish;
    private int rate;
    public String description;
    private List<Item_Ingredient> ingredients;

    public itemFood(int image, String name, boolean isFavourite, String timeFinish, int rate, String description, List<Item_Ingredient> ingredients) {
        this.image = image;
        this.name = name;
        this.isFavourite = isFavourite;
        this.timeFinish = timeFinish;
        this.rate = rate;
        this.description = description;
        this.ingredients = ingredients;
    }
}
