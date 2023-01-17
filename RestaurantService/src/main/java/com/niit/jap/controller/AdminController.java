package com.niit.jap.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.niit.jap.domain.Admin;
import com.niit.jap.domain.Menu;
import com.niit.jap.domain.Order;
import com.niit.jap.domain.Restaurant;
import com.niit.jap.exception.ItemAlreadyExistsException;
import com.niit.jap.service.AdminService;
import com.niit.jap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v2/ad")
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;

    @Autowired
    public AdminController(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

//    @PostMapping("/saverestaurant1")
//    public ResponseEntity<?> saveRestaurant(@RequestBody Restaurant restaurant) {
//        return new ResponseEntity<>(this.adminService.saveRestaurant(restaurant), HttpStatus.CREATED);
//    }

    @PostMapping("/saverestaurant")
    public ResponseEntity<?> saveRestaurant(@RequestParam("file") MultipartFile file, @RequestParam("restaurant") String restaurant) throws IOException {
//       List<Menu> menu= new ObjectMapper().readValue(restaurant., Menu.class);
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurant restaurant1 = objectMapper.readValue(restaurant, Restaurant.class);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        Restaurant dbRestaurant = adminService.saveRestaurant(restaurant1, file);
        return new ResponseEntity<>(dbRestaurant, HttpStatus.OK);
    }

    @PostMapping("/saveorder")
    public ResponseEntity<?> saveOrder(@RequestBody Order order) {
        return new ResponseEntity<>(this.userService.saveOrder(order), HttpStatus.CREATED);
    }

    @GetMapping("/getallrestaurants")
    public ResponseEntity<?> getAllRestaurants() {
        return new ResponseEntity<>(this.adminService.getAllRestaurants(), HttpStatus.ACCEPTED);
    }

    @PutMapping("/updaterestaurant")
    public ResponseEntity<?> updateRestaurant(@RequestParam("file") MultipartFile file, @RequestParam("restaurant") String restaurant) throws IOException {
        Restaurant restaurant1 = new ObjectMapper().readValue(restaurant, Restaurant.class);
        Restaurant dbRestaurant = adminService.updateRestaurant(restaurant1, file);
        return new ResponseEntity<>(dbRestaurant, HttpStatus.OK);
    }

    //    @DeleteMapping("/delete/{restaurantName}")
//    public ResponseEntity<?> deleteRestaurant(@PathVariable String restaurantName) {
//        return new ResponseEntity<>(this.adminService.deleteRestaurant(restaurantName), HttpStatus.ACCEPTED);
//    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable String id) {
        return new ResponseEntity<>(this.adminService.deleteRestaurant(id), HttpStatus.ACCEPTED);
    }

    @PostMapping("/saveadmin")
    public ResponseEntity<?> saveOrder(@RequestBody Admin admin) {
        return new ResponseEntity<>(this.adminService.saveAdmin(admin), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getbycity/{cityName}")
    public ResponseEntity<?> getRestaurantByCityName(@PathVariable String cityName) {
        return new ResponseEntity<>(this.adminService.getRestaurantsByCity(cityName), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getallusers")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(this.adminService.getAllUsers(), HttpStatus.OK);
    }

    //    @PostMapping("/saverestaurant")
//    public ResponseEntity<?> saveRestaurant(@RequestParam("file") MultipartFile file, @RequestParam ("restaurant") String restaurant) throws IOException {
//        Restaurant restaurant1 = new ObjectMapper().readValue(restaurant, Restaurant.class);
//        restaurant1.setUrl(file.getBytes());
//        restaurant1.setFileName(file.getOriginalFilename());
//        Restaurant dbRestaurant = adminService.saveRestaurant(restaurant1);
//        return new ResponseEntity<>(dbRestaurant, HttpStatus.CREATED);
//    }
    @GetMapping("/restaurant/{id}")
    public ResponseEntity<?> getRestaurantById(@PathVariable String id) {
        return new ResponseEntity<>(this.adminService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/updatemenu/{id}")
    public ResponseEntity<?> updateRestaurant(@RequestBody Menu menu,@PathVariable String id) throws ItemAlreadyExistsException {
        return new ResponseEntity<>(this.adminService.updateMenu(menu,id), HttpStatus.OK);
    }

    @PutMapping("/deletemenu/{id}")
    public ResponseEntity<?> deleteMenu(@RequestBody List<Menu> menu, @PathVariable String id) {
        return new ResponseEntity<>(this.adminService.deleteMenu(menu,id), HttpStatus.OK);
    }
}
