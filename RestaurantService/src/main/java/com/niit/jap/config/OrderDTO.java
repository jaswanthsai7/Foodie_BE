package com.niit.jap.config;

import com.niit.jap.domain.Address;
import com.niit.jap.domain.Restaurant;

public class OrderDTO {
    private int orderId;
    private String userEmailId;
    private Restaurant restaurant;
    private Address address;
    private double totalAmount;

    public OrderDTO() {
    }

    public OrderDTO(int orderId, String userEmailId, Restaurant restaurant, Address address, double totalAmount) {
        this.orderId = orderId;
        this.userEmailId = userEmailId;
        this.restaurant = restaurant;
        this.address = address;
        this.totalAmount = totalAmount;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUserEmailId() {
        return userEmailId;
    }

    public void setUserEmailId(String userEmailId) {
        this.userEmailId = userEmailId;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
