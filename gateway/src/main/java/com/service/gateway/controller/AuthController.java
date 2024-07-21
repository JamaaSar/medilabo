package com.service.gateway.controller;

import com.service.gateway.dto.CredentialsDTO;
import com.service.gateway.dto.UserDTO;
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

    /**
     * Handles user login and returns a JWT token.
     *
     * This endpoint processes login requests by validating user credentials.
     * If the credentials are correct, a JWT token is generated and returned
     * along with the user details.
     *
     * @param credentialsDto the credentials provided by the user, including username and password.
     * @return a ResponseEntity containing a UserDTO with the username and JWT token if authentication is successful.
     */
    @PostMapping("/auth/login")
    public ResponseEntity<UserDTO> login(@RequestBody CredentialsDTO credentialsDto) {
        User user=
                customUserDetailsService.findByUsername(credentialsDto.username());
        String token= jwtService.generateToken(user);
        UserDTO userDTO = new UserDTO(user.getUsername(), token);

        return ResponseEntity.ok(userDTO);
    }
}
