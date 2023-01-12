package com.niit.jap.services;

import com.niit.jap.domain.Order;
import com.niit.jap.domain.User;

import java.util.List;

public interface OrderService {
//    User saveOrder(Order order,String emailId);
    User saveUser(User user);

    User updateOrder(User user);

    List<User> getAllUsers();

    User getByEmailId(String emailId);
}
