package com.service.gateway.service;

import com.service.gateway.dto.CredentialsDTO;
import com.service.gateway.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    JWTService jwtService;
    public UserDTO login(CredentialsDTO credentialsDTO) {

        User user=
            customUserDetailsService.findByUsername(credentialsDTO.username());
        String token= jwtService.generateToken(user);
        UserDTO userDTO = new UserDTO(user.getUsername(), token);
        return userDTO;
    }
}