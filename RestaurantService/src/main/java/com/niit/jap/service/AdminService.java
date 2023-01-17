package com.niit.jap.service;

import com.niit.jap.domain.Admin;
import com.niit.jap.domain.Menu;
import com.niit.jap.domain.Restaurant;
import com.niit.jap.domain.User;
import com.niit.jap.exception.ItemAlreadyExistsException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface AdminService {
    List<Restaurant> getAllRestaurants();

    List<Restaurant> getRestaurantsByCity(String cityName);

    Restaurant saveRestaurant(Restaurant restaurant, MultipartFile file) throws IOException;

    boolean deleteRestaurant(String id);

    Restaurant updateRestaurant(Restaurant restaurant, MultipartFile file) throws IOException;

    Admin saveAdmin(Admin admin);

    List<User> getAllUsers();
    Optional<Restaurant> findById(String id);

    Restaurant deleteMenu(List<Menu> menuList,String id);

    Restaurant updateMenu(Menu menu, String id) throws ItemAlreadyExistsException;
}