package com.example.samta_ai.service.impl;

import com.example.samta_ai.model.User;
import com.example.samta_ai.repo.UserRepository;
import com.example.samta_ai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createUser(User user) {
        this.userRepository.save(user);
    }
}
