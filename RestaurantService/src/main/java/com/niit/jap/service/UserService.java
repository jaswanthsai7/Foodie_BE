package com.niit.jap.service;

import com.niit.jap.domain.Address;
import com.niit.jap.domain.Order;
import com.niit.jap.domain.Restaurant;
import com.niit.jap.domain.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    Order saveOrder(Order order);

    List<Restaurant> getAllRestaurants();

    List<Restaurant> getRestaurantsByCity(String cityName);

    User saveAddress(Address address, String emailId);

    User updateUser(User user);

    User getUserByEmail(String emailId);
}
