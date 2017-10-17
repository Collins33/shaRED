package com.example.root.shared;

/**
 * Created by mwas on 10/16/17.
 */

public class Category {
    private String name;
    private int image;

    public Category() {}

    public Category(String name, int image){
        this.name = name;
        this.image = image;
    }

    public String getName(){
        return name;
    }

    public int getImage() {
        return image;
    }
}
