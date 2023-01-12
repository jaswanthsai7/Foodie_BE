package com.niit.jap.domain;

import java.util.List;

public class Restaurant {

    private String id;
    private String restaurantName;
    private Image restaurantImage;
    private String description;
    private String city;
    private List<Menu> menuList;

    public Restaurant() {
    }

    public Restaurant(String id, String restaurantName, Image restaurantImage, String description, String city, List<Menu> menuList) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.restaurantImage = restaurantImage;
        this.description = description;
        this.city = city;
        this.menuList = menuList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Image getRestaurantImage() {
        return restaurantImage;
    }

    public void setRestaurantImage(Image restaurantImage) {
        this.restaurantImage = restaurantImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }
}
