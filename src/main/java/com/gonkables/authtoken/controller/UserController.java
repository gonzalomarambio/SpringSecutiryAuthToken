package com.gonkables.authtoken.controller;

import com.gonkables.authtoken.entities.UserTo;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/mensaje")
    public ResponseEntity<String> getMensaje(@AuthenticationPrincipal UserTo user){
        return ResponseEntity.ok("Hola");
    }
}
