package com.service.gateway.controller;

import com.service.gateway.dto.CredentialsDTO;
import com.service.gateway.service.CustomUserDetailsService;
import com.service.gateway.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    JWTService jwtService;
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody CredentialsDTO credentialsDto) {
        User user=
                customUserDetailsService.findByUsername(credentialsDto.username());
        String token= jwtService.generateToken(user);

        return ResponseEntity.ok(token);
    }
}
