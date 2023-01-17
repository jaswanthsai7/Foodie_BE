package com.niit.jap.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.jap.domain.User;
import com.niit.jap.exception.UserEmailAlreadyExistsException;
import com.niit.jap.exception.UserNotFoundException;
import com.niit.jap.service.SecurityTokenGenerator;
import com.niit.jap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;
    private final SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    //    @PostMapping("/save")
//    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserEmailAlreadyExistsException {
//        boolean check = userService.getUserByEmailId(user.getEmailId());
//        if (!check) {
//            return new ResponseEntity<>(this.userService.saveUser(user), HttpStatus.CREATED);
//        } else {
//            throw new UserEmailAlreadyExistsException();
//        }
//    }
    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestParam("file") MultipartFile file, @RequestParam("user") String user) throws UserEmailAlreadyExistsException, IOException {
        User user1 = new ObjectMapper().readValue(user, User.class);
        boolean check = userService.getUserByEmailId(user1.getEmailId());
        if (!check) {
            User user2 = userService.saveUser(user1, file);
            return new ResponseEntity<>(user2, HttpStatus.CREATED);
        } else {
            throw new UserEmailAlreadyExistsException();
        }
    }


    @GetMapping("/admin/getall")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(this.userService.getAll(), HttpStatus.OK);
    }

    //    @GetMapping("/admin/getbyid/{id}")
//    public ResponseEntity<?> getUserById(@PathVariable int id) {
//        return new ResponseEntity<>(this.userService.getUserById(id), HttpStatus.OK);
//    }
    @GetMapping("/admin/getbyid/{emailId}")
    public ResponseEntity<?> getUserById(@PathVariable String emailId) throws UserEmailAlreadyExistsException {
        return new ResponseEntity<>(this.userService.getUserByEmailId(emailId), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> getUserByUserNameAndPassword(@RequestBody User user) throws UserNotFoundException {
        try {
            User user1 = this.userService.getUserByEmailIdAndPassword(user.getEmailId(), user.getPassword());
            if (Objects.equals(user1.getRole(), "admin")) {
                new ResponseEntity<>(this.userService.getUserByEmailIdAndPassword(user.getEmailId(), user.getPassword()), HttpStatus.OK);
                Map<String, String> key = new HashMap<>();
                key = this.securityTokenGenerator.generateToken(user);
                return new ResponseEntity<>(key, HttpStatus.OK);
//                Map<String, String> map = new HashMap<>();
//                map.put("message", "User logged in successfully");
//                map.put("token", this.securityTokenGenerator.generateToken(user1).toString());
//                return new ResponseEntity<>(map, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(user1, HttpStatus.OK);
            }

        } catch (UserNotFoundException exception) {
//                    Map<String, String> map = new HashMap<>();
//                    map.put("message", "User not found");
            throw new UserNotFoundException();
//                    return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
        }
//            new ResponseEntity<>(this.userService.getUserByEmailIdAndPassword(user.getEmailId(), user.getPassword()), HttpStatus.OK);
//            Map<String, String> key = new HashMap<>();
//            key= this.securityTokenGenerator.generateToken(user);
//            return new ResponseEntity<>(key, HttpStatus.OK);
    }

    @DeleteMapping("/admin/deletebyid/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        this.userService.deleteUserById(id);
        return new ResponseEntity<>("User Deleted", HttpStatus.OK);
    }

    @DeleteMapping("/admin/deletebyemail/{emailId}")
    public ResponseEntity<?> deleteUserByEmailId(@PathVariable String emailId) throws UserNotFoundException {
        try {
            boolean check = userService.getUserByEmailId(emailId);
            if (!check) {
                throw new UserNotFoundException();
            } else {
                this.userService.deleteUserByEmailId(emailId);
                return new ResponseEntity<>("User Deleted", HttpStatus.OK);
            }
        } catch (UserEmailAlreadyExistsException ex) {
            throw new RuntimeException(ex);
        }
    }

    @PutMapping("/updateuser")
    public ResponseEntity<?> updateUser(@RequestBody User user) throws UserNotFoundException {
        return new ResponseEntity<>(this.userService.updateUser(user), HttpStatus.OK);
    }

}
