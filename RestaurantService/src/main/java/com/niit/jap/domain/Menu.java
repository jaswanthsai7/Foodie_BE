package com.niit.jap.domain;

public class Menu {

    private String itemName;
    private String description;
    private int quantity;
    private double itemPrice;

    public Menu() {
    }

    public Menu(String itemName, String description, int quantity, double itemPrice) {
        this.itemName = itemName;
        this.description = description;
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
}
