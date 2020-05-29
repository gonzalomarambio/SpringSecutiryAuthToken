package com.gonkables.authtoken.service;

import com.gonkables.authtoken.entities.UserTo;

public interface UserService {

    UserTo findUserbyUsername(String username);
}
