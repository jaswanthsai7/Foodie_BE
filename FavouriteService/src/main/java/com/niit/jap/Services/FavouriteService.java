package com.niit.jap.Services;

import com.niit.jap.Domain.Dish;
import com.niit.jap.Domain.Restaurant;
import com.niit.jap.Domain.User;
import com.niit.jap.Exception.DishAlreadyExistException;
import com.niit.jap.Exception.RestaurantAlreadyExistException;
import com.niit.jap.Exception.UnableToFetchFavouritesException;

import java.util.List;
import java.util.Optional;

public interface FavouriteService {

    User saveUser(User user);

    User updateUser(User user);

    void deleteUser(String emailId);

    List<User> getAllUser();

    User getByEmail(String email);

    User addFavouriteRestaurantToUser(Restaurant restaurant , String emailId) throws RestaurantAlreadyExistException;

    User addFavouriteDishToUser(Dish dish , String emailId) throws DishAlreadyExistException;

    User removeFavouriteDishFromUser(String itemName , String emailId) throws UnableToFetchFavouritesException;

    User removeFavouriteRestaurantFromUser(String restaurantName , String emailId) throws UnableToFetchFavouritesException;



}
