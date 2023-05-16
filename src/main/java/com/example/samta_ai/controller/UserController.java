package com.example.samta_ai.controller;

import com.example.samta_ai.model.User;
import com.example.samta_ai.repo.UserRepository;
import com.example.samta_ai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping("/")
    public ResponseEntity<String> createUser (@RequestBody User user) {

        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        this.userService.createUser(user);
        return ResponseEntity.ok("User Created Successfully");

    }
}
