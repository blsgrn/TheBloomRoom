package com.example.thebloomroom_105_53;

public class Flower {
    private int id;
    private String name;
    private String description;
    private double price;
    private String category;
    private String imageFilename;

    public Flower(int id, String name, String description, double price, String category, String imageFilename) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.imageFilename = imageFilename;
    }

//    getters and setters
public int getId() {
    return id;
}


    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }



    public double getPrice() {
        return price;
    }



    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageFilename() {
        return imageFilename;
    }




}
