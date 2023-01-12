package com.niit.jap.service;

import com.niit.jap.domain.*;
import com.niit.jap.repository.AdminRepository;
import com.niit.jap.repository.RestaurantRepository;
import com.niit.jap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class AdminServiceImpl implements AdminService {

    private final RestaurantRepository restaurantRepository;
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    @Autowired
    public AdminServiceImpl(RestaurantRepository restaurantRepository, AdminRepository adminRepository, UserRepository userRepository) {
        this.restaurantRepository = restaurantRepository;
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<Restaurant> getAllRestaurants() {
        return this.restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> getRestaurantsByCity(String cityName) {
        return this.restaurantRepository.findRestaurantByCity(cityName);
    }

    //    @Override
//    public Restaurant saveRestaurant(Restaurant restaurant) {
//        return this.restaurantRepository.save(restaurant);
//    }
    @Override
    public Restaurant saveRestaurant(Restaurant restaurant, MultipartFile file) throws IOException {
        Image img = new Image(file.getBytes(), file.getOriginalFilename());
        restaurant.setRestaurantImage(img);
        List<Menu> menus = restaurant.getMenuList();
        restaurant.setMenuList(menus);
        return this.restaurantRepository.save(restaurant);
    }

    @Override
    public boolean deleteRestaurant(String id) {
        if (this.restaurantRepository.existsById(id)) {
            this.restaurantRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }

//    @Override
//    public Restaurant updateRestaurant(Restaurant restaurant) {
//        if (this.restaurantRepository.existsById(restaurant.getRestaurantName())) {
//            this.restaurantRepository.save(restaurant);
//        }
//        return restaurant;
//    }

    @Override
    public Restaurant updateRestaurant(Restaurant restaurant, MultipartFile file) throws IOException {
        if (this.restaurantRepository.existsById(restaurant.getId())) {
           Optional<Restaurant> restaurant1= this.restaurantRepository.findById(restaurant.getId());
            Image img = new Image(file.getBytes(), file.getOriginalFilename());
            restaurant.setRestaurantImage(img);
            restaurant.setMenuList(restaurant1.get().getMenuList());
            this.restaurantRepository.save(restaurant);
        }
        return restaurant;
    }

    @Override
    public Admin saveAdmin(Admin admin) {
        return this.adminRepository.save(admin);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<Restaurant> findById(String id) {
        return this.restaurantRepository.findById(id);
    }

    @Override
    public Restaurant deleteMenu(List<Menu> menuList,String id) {
       Optional<Restaurant> restaurant=this.restaurantRepository.findById(id);
       restaurant.get().setMenuList(menuList);
       return this.restaurantRepository.save(restaurant.get());
    }

    @Override
    public Restaurant updateMenu(Menu menu, String id) {
        List<Restaurant> restaurants = this.restaurantRepository.findAll();
        Optional<Restaurant> restaurant = restaurants.stream().filter(item -> Objects.equals(item.getId(), id)).findFirst();
        List<Menu> menus = restaurant.get().getMenuList();
        if (menus == null) {
            restaurant.get().setMenuList(Collections.singletonList(menu));
        } else {
            Optional<Menu> food = menus.stream().filter(item -> Objects.equals(item.getItemName(), menu.getItemName())).findFirst();
            if (food.isEmpty()) {
                menus.add(menu);
                restaurant.get().setMenuList(menus);
            } else {
                System.out.println("Menu Item Exists");
            }
        }
        return this.restaurantRepository.save(restaurant.get());
    }
}
