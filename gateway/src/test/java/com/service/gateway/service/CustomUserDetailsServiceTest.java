package com.service.gateway.service;

import com.service.gateway.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CustomUserDetailsServiceTest {
    @InjectMocks
    private CustomUserDetailsService service;

    @ParameterizedTest
    @CsvSource({
            "user, user, password, USER",
            "admin, admin, admin, ADMIN"
    })
    void testFindByUsername(String inputUsername, String expectedUsername, String expectedPassword, String expectedRole) {
        User user = service.findByUsername(inputUsername);
        assertEquals(expectedUsername, user.getUsername());
        assertEquals(expectedPassword, user.getPassword());
        assertEquals(1, user.getAuthorities().size());
        assertEquals("ROLE_" + expectedRole, user.getAuthorities().iterator().next().getAuthority());

    }
    @Test
    void testThrowNotFound() {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            service.findByUsername("test");
        });

        assertEquals("Utilisateur inconnu", exception.getMessage());

    }
}