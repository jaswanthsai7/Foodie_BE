package com.niit.jap.Services;

import com.niit.jap.Domain.Dish;

import com.niit.jap.Domain.Restaurant;
import com.niit.jap.Domain.User;
import com.niit.jap.Exception.DishAlreadyExistException;
import com.niit.jap.Exception.RestaurantAlreadyExistException;
import com.niit.jap.Exception.UnableToFetchFavouritesException;
import com.niit.jap.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class FavouriteServiceImpl implements FavouriteService{

    private UserRepository userRepository;

    @Autowired
    public FavouriteServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override     //saving the user
    public User saveUser(User user) {
        if (userRepository.findById(user.getEmailId()).isPresent()){
            throw new RuntimeException("User Already Exist");
        }
        else {
            return userRepository.save(user);
        }
    }

    @Override      //updating the user
    public User updateUser(User user) {

      User user1 = null;
      Optional<User> userById = userRepository.findById(user.getEmailId());

      if(userById.isPresent()){
          user1 = userById.get();
          user1.setDishList(user.getDishList());
          user1.setRestaurantList(user.getRestaurantList());
          userRepository.save(user1);
      }
      else {
          return  new User();
      }
      return user1;
    }

    @Override   //deleting the user
    public void deleteUser(String emailId) {

    userRepository.deleteById(emailId);
    }

    @Override  //getting all the user
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override    // adding fav restaurants to user
    public User addFavouriteRestaurantToUser(Restaurant restaurant, String emailId) throws RestaurantAlreadyExistException {

        User user =  userRepository.findByEmailId(emailId);

        List<Restaurant> restaurantList= user.getRestaurantList();

        List<Dish> dishList = user.getDishList();

        if(user.getRestaurantList()==null)
        {
            user.setRestaurantList(Arrays.asList(restaurant));
            user.setDishList(dishList);
        }
        else
        {
            Optional<Restaurant> rest=restaurantList.stream().filter(item-> Objects.equals(item.getId(), restaurant.getId())).findFirst();

            if (rest.isEmpty())
            {
                restaurantList.add(restaurant);
                user.setRestaurantList(restaurantList);
                user.setDishList(dishList);
            }
          else
            {
//                user.setRestaurantList(restaurantList);
                throw new RestaurantAlreadyExistException();
            }
        }
        return userRepository.save(user);
    }

    @Override   //adding fav dish to user
    public User addFavouriteDishToUser(Dish dish, String emailId) throws DishAlreadyExistException {

        User user = userRepository.findByEmailId(emailId);

        List<Dish> dishList = user.getDishList();

        List<Restaurant> restaurantList=user.getRestaurantList();

        if(user.getDishList()==null)
        {
            user.setDishList(Arrays.asList(dish));
            user.setRestaurantList(restaurantList);
        }
        else {

            Optional<Dish> food = dishList.stream().filter(item -> Objects.equals(item.getItemName(),dish.getItemName())).findFirst();

            if (food.isEmpty())
            {
                dishList.add(dish);
                user.setDishList(dishList);
                user.setRestaurantList(restaurantList);
            }
            else
            {
                throw new DishAlreadyExistException();
            }
        }

        return userRepository.save(user);
    }

    @Override        //remove fav dish from user
    public User removeFavouriteDishFromUser(String itemName, String emailId) throws UnableToFetchFavouritesException {

        User user = userRepository.findByEmailId(emailId);
        Optional<Dish> newList = user.getDishList().stream().filter(item -> Objects.equals(item.getItemName(), itemName)).findFirst();

        if (newList.isEmpty())
        {
            throw new UnableToFetchFavouritesException();
        }
        else
        {
            user.getDishList().removeIf(dish -> dish.getItemName().equals(itemName));
            return userRepository.save(user);
        }
    }

    @Override          //remove fav restaurant from user
    public User removeFavouriteRestaurantFromUser(String restaurantName, String emailId) throws UnableToFetchFavouritesException {

        User user = userRepository.findByEmailId(emailId);

        Optional<Restaurant> newList = user.getRestaurantList().stream().filter(rest-> Objects.equals(rest.getRestaurantName(), restaurantName)).findFirst();

        if(newList.isEmpty())
        {
            throw new UnableToFetchFavouritesException();
        }
        else
        {
            user.getRestaurantList().removeIf(restaurant -> restaurant.getRestaurantName().equals(restaurantName));
            return userRepository.save(user);
        }


    }

    @Override    //get user by email id
    public User getByEmail(String email) {

        return userRepository.findByEmailId(email);
    }
}
