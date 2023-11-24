package com.example.thebloomroom_105_53;

public class CartItem {
    private int itemId;
    private String itemName;
    private double itemPrice;
    private String itemCategory;

    public CartItem(int id, String name, double price, String category) {
        this.itemId = id;
        this.itemName = name;
        this.itemPrice = price;
        this.itemCategory = category;
    }

    // getters and setters
    public int getItemId() {
        return itemId;
    }

    public String getName() {
        return itemName;
    }

    public void setName(String name) {
        this.itemName = name;
    }

    public double getPrice() {
        return itemPrice;
    }


    public String getCategory() {
        return itemCategory;
    }

    public void setCategory(String category) {
        this.itemCategory = category;
    }
}
