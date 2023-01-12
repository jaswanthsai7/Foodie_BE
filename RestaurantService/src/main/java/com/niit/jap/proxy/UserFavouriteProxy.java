package com.niit.jap.proxy;

import com.niit.jap.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "favourite-service",url = "favourite-service:8086")
public interface UserFavouriteProxy {
    @PostMapping("/api/v3/saveUser")
    public ResponseEntity<?> saveUser(@RequestBody User user);
}
