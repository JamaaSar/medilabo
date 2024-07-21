package com.service.gateway.service;

import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService {

    public User findByUsername(String username) {
        if ("user".equals(username)) {
            return
                    (User) User.withUsername("user")
                            .password("password") // {noop} indicates no encoding is used
                            .roles("USER")
                            .build(
            );
        } else if ("admin".equals(username)) {
            return
                    (User) User.withUsername("admin")
                            .password("admin") // {noop} indicates no encoding is used
                            .roles("ADMIN")
                            .build(
            );
        } else {
            throw new NotFoundException("Utilisateur inconnu");
        }
    }
}