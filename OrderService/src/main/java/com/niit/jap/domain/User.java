package com.niit.jap.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "User")
public class User {

    @Id
    private String emailId;

    private List<Order> orders;

    public User() {
    }

    public User(String emailId, List<Order> orders) {
        this.emailId = emailId;
        this.orders = orders;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
