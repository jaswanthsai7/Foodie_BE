package com.niit.jap.Domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document
public class User {

    @MongoId
    private String emailId;
    private List<Restaurant> restaurantList;
    private List<Dish> dishList;

    public User() {

    }

    public User(String emailId, List<Restaurant> restaurantList, List<Dish> dishList) {
        this.emailId = emailId;
        this.restaurantList = restaurantList;
        this.dishList = dishList;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    public List<Dish> getDishList() {
        return dishList;
    }

    public void setDishList(List<Dish> dishList) {
        this.dishList = dishList;
    }

}





