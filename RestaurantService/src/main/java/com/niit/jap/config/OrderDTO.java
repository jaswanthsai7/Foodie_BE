package com.niit.jap.config;

import com.niit.jap.domain.Address;
import com.niit.jap.domain.Menu;
import com.niit.jap.domain.Restaurant;

import java.util.Date;
import java.util.List;

public class OrderDTO {
    private int orderId;
    private String userEmailId;
    private List<Menu> items;
    private Date date;
    private Address address;
    private double totalAmount;

    public OrderDTO() {
    }

    public OrderDTO(int orderId, String userEmailId, List<Menu> items, Date date, Address address, double totalAmount) {
        this.orderId = orderId;
        this.userEmailId = userEmailId;
        this.items = items;
        this.date = date;
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

    public List<Menu> getItems() {
        return items;
    }

    public void setItems(List<Menu> items) {
        this.items = items;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
