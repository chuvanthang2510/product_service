package com.savvycom.product_service.service;

import com.savvycom.product_service.domain.feign.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "auth-service", url = "http://10.22.4.132:8888/auth")
public interface UserService {

    @GetMapping("/feign/username/{username}")
    Optional<User> getUserByUsername(@PathVariable("username") String username);
}
