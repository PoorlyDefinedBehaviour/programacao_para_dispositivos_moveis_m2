package com.example.m2;

import android.graphics.Bitmap;

public class MenuItem {
    public int id;
    public String name;
    public String description;
    public String price;
    public boolean hasGluten;
    public int calories;
    public Bitmap image;

    public MenuItem(int id, String name, String description, String price, boolean hasGluten, int calories, Bitmap image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.hasGluten = hasGluten;
        this.calories = calories;
        this.image = image;
    }
}
