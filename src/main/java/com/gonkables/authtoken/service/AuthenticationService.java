package com.gonkables.authtoken.service;

import com.gonkables.authtoken.entities.UserTo;

public interface AuthenticationService {

    UserTo authenticate(String username, char[] password);

    UserTo getValidUserToken(String token);
}
