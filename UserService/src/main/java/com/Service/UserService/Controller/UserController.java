package com.Service.UserService.Controller;

import com.Service.UserService.dto.CreateUserRequest;
import com.Service.UserService.model.User;
import com.Service.UserService.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/user")
    public User createUser(@RequestBody @Valid CreateUserRequest userRequest){
        return userService.createUser(userRequest);
    }

    @GetMapping("/user")
    public User getUser(@RequestParam("PhoneNo") String phoneNo){
        return userService.getUserByPhoneNo(phoneNo);
    }
}
