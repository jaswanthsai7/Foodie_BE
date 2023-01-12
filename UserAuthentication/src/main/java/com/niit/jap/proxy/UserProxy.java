package com.niit.jap.proxy;

import com.niit.jap.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "restaurant-service",url = "restaurant-service:8085")
public interface UserProxy {
    @PostMapping("/api/v2/saveuser")
    public ResponseEntity<?> saveUser(@RequestBody User user);
    @PostMapping("/api/v2/ad/saveadmin")
    public ResponseEntity<?> saveAdmin(@RequestBody User user);
}
