package com.jerrycodes.emis.controller;

import com.jerrycodes.emis.entity.User;
import com.jerrycodes.emis.model.UserModel;
import com.jerrycodes.emis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity<User> saveUser(@RequestBody UserModel userModel){
        User newUser = userService.saveUser(userModel);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}
