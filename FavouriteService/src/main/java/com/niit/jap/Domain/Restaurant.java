package com.niit.jap.Domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Restaurant {

    @Id
    private String id;
    private String restaurantName;
    private String description;
    private Image restaurantImage;
    private String city;
    private List<Menu> menuList;

    public Restaurant() {
    }

    public Restaurant(String id, String restaurantName, String description, Image restaurantImage, String city, List<Menu> menuList) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.description = description;
        this.restaurantImage = restaurantImage;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Image getRestaurantImage() {
        return restaurantImage;
    }

    public void setRestaurantImage(Image restaurantImage) {
        this.restaurantImage = restaurantImage;
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
