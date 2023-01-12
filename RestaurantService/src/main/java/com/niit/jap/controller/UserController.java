package com.niit.jap.controller;

import com.niit.jap.domain.Address;
import com.niit.jap.domain.Order;
import com.niit.jap.domain.User;
import com.niit.jap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v2/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/saveorder")
    public ResponseEntity<?> saveOrder(@RequestBody Order order) {
        return new ResponseEntity<>(this.userService.saveOrder(order), HttpStatus.ACCEPTED);
    }

    @PostMapping("/saveuser")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        return new ResponseEntity<>(this.userService.saveUser(user), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getbycity/{cityName}")
    public ResponseEntity<?> getRestaurantByCityName(@PathVariable String cityName) {
        return new ResponseEntity<>(this.userService.getRestaurantsByCity(cityName), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getallrestaurants")
    public ResponseEntity<?> getAllRestaurants() {
        return new ResponseEntity<>(this.userService.getAllRestaurants(), HttpStatus.ACCEPTED);
    }

    @PutMapping("/saveaddress/{emailId}")
    public ResponseEntity<?> saveAddress(@RequestBody Address address, @PathVariable String emailId) {
        return new ResponseEntity<>(this.userService.saveAddress(address, emailId), HttpStatus.ACCEPTED);
    }
}
