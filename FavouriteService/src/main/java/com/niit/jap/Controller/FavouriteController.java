package com.niit.jap.Controller;

import com.niit.jap.Domain.Dish;
import com.niit.jap.Domain.Restaurant;
import com.niit.jap.Domain.User;
import com.niit.jap.Exception.DishAlreadyExistException;
import com.niit.jap.Exception.RestaurantAlreadyExistException;
import com.niit.jap.Exception.UnableToFetchFavouritesException;
import com.niit.jap.Services.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v3")
public class FavouriteController {

    private FavouriteService favouriteService;

    @Autowired
    public FavouriteController(FavouriteService favouriteService) {
        this.favouriteService = favouriteService;
    }

    @GetMapping("/getUser/{emailId}")
    public ResponseEntity<?> getEmailId(@PathVariable String emailId){

        return new ResponseEntity<>(favouriteService.getByEmail(emailId) , HttpStatus.FOUND);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<?> updatingUser(@RequestBody User user){
        return new ResponseEntity<>(favouriteService.updateUser(user),HttpStatus.OK);
    }

    @PostMapping("/saveUser")
    public ResponseEntity<?> savingUser(@RequestBody User user){
        return new ResponseEntity<>(favouriteService.saveUser(user),HttpStatus.OK);

    }

    @GetMapping("/getAllUser")
    public ResponseEntity<?> gettingAllUser(){
        return new ResponseEntity<>(favouriteService.getAllUser(),HttpStatus.FOUND);
    }

    @DeleteMapping("/deleteUser/{emailId}")
    public ResponseEntity<?> deletingUser(@PathVariable String emailId){
        favouriteService.deleteUser(emailId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/saveFavRestaurant/{emailId}")
    public ResponseEntity<?> addFavouriteRestaurant(@RequestBody Restaurant restaurant , @PathVariable String emailId) throws RestaurantAlreadyExistException {

        try {
            return new ResponseEntity<>(favouriteService.addFavouriteRestaurantToUser(restaurant,emailId),HttpStatus.OK);
        }
        catch (RestaurantAlreadyExistException e) {
            throw new RestaurantAlreadyExistException();
        }
    }

    @PutMapping("/saveFavDish/{emailId}")
    public ResponseEntity<?> addFavouriteDish(@RequestBody Dish dish ,@PathVariable String emailId) throws DishAlreadyExistException {
        try {
            return new ResponseEntity<>(favouriteService.addFavouriteDishToUser(dish, emailId),HttpStatus.OK);
        }
        catch (DishAlreadyExistException e) {
            throw new DishAlreadyExistException();
        }
    }

    @DeleteMapping("/removeFavRestaurant/{restaurantName}/{emailId}")
    public ResponseEntity<?> deleteFavouriteRestaurant (@PathVariable String restaurantName , @PathVariable String emailId) throws UnableToFetchFavouritesException {
        try {
            favouriteService.removeFavouriteRestaurantFromUser(restaurantName, emailId);
        } catch (UnableToFetchFavouritesException e) {
            throw new UnableToFetchFavouritesException();
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/removeFavDish/{itemName}/{emailId}")
    public ResponseEntity<?> deleteFavouriteDish( @PathVariable String itemName , @PathVariable String emailId) throws UnableToFetchFavouritesException {
        try {
            favouriteService.removeFavouriteDishFromUser(itemName, emailId);
        } catch (UnableToFetchFavouritesException e) {
            throw new UnableToFetchFavouritesException();
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


}
