package com.gonkables.authtoken.controller;

import com.gonkables.authtoken.entities.UserTo;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @PostMapping("/login")
    public ResponseEntity<String> login(@AuthenticationPrincipal UserTo userTo){
        return ResponseEntity.ok(userTo.getToken());
    }
}
