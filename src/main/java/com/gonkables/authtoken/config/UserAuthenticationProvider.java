package com.gonkables.authtoken.config;

import com.gonkables.authtoken.entities.UserTo;
import com.gonkables.authtoken.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(authentication instanceof UsernamePasswordAuthenticationToken){
            String login = authentication.getPrincipal().toString();
            char[] password = (char[]) authentication.getCredentials();
            UserTo userTo = null;
            userTo = authenticationService.authenticate(login, password);
            return new UsernamePasswordAuthenticationToken(userTo, null, Collections.emptyList());
        }else if(authentication instanceof PreAuthenticatedAuthenticationToken){
            UserTo userTo = authenticationService.getValidUserToken(authentication.getPrincipal().toString());
            //TODO validar que token no este expirado
            return new UsernamePasswordAuthenticationToken(userTo, null, Collections.emptyList());
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
