package com.gonkables.authtoken.service.impl;


import com.gonkables.authtoken.entities.UserTo;
import com.gonkables.authtoken.repository.UserRepository;
import com.gonkables.authtoken.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserTo authenticate(String username, char[] password) {
        UserTo userTo = userRepository.findByUsermane(username);
        if(userTo != null){
            String userPasswordDb = passwordEncoder.encode(CharBuffer.wrap(userTo.getPassword()));
            if (passwordEncoder.matches(CharBuffer.wrap(password), userPasswordDb)) {
                //TODO implementar jwt para token
                userTo.setToken("implementar_token_jwt_id" + userTo.getId());
                userRepository.save(userTo);
                return userTo;
            }
        }
        throw new RuntimeException("Invalid User/Password");
    }

    @Override
    public UserTo getValidUserToken(String token) {

        UserTo userTo = userRepository.findByToken(token);
        if(userTo != null){
            //TODO implementar validacion de token
            return userTo;
        }
        throw new RuntimeException("Invalid Token");
    }
}
