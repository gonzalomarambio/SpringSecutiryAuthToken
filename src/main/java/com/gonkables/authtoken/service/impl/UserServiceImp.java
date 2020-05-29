package com.gonkables.authtoken.service.impl;

import com.gonkables.authtoken.entities.UserTo;
import com.gonkables.authtoken.repository.UserRepository;
import com.gonkables.authtoken.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserTo findUserbyUsername(String username) {
        return userRepository.findByUsermane(username);
    }
}
