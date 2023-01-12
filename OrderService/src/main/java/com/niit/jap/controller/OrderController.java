package com.niit.jap.controller;

import com.niit.jap.domain.Order;
import com.niit.jap.domain.User;
import com.niit.jap.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v4")
public class OrderController {
    private OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/saveuser")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        return new ResponseEntity<>(this.orderService.saveUser(user), HttpStatus.CREATED);
    }
//    @PutMapping("/saveorder/{email}")
//    public ResponseEntity<?> saveOrder(@RequestBody Order order, @PathVariable String email){
//        return new ResponseEntity<>(this.orderService.saveOrder(order,email), HttpStatus.CREATED);
//    }
    @PutMapping("/updateorder")
    public ResponseEntity<?> updateOrder(@RequestBody User user){
        return new ResponseEntity<>(this.orderService.updateOrder(user), HttpStatus.CREATED);
    }
    @GetMapping("/getallusers")
    public ResponseEntity<?> updateOrder(){
        return new ResponseEntity<>(this.orderService.getAllUsers(), HttpStatus.CREATED);
    }
    @GetMapping("/getuser/{emailId}")
    public ResponseEntity<?> getUser(@PathVariable String emailId){
        return new ResponseEntity<>(this.orderService.getByEmailId(emailId), HttpStatus.CREATED);
    }
}
