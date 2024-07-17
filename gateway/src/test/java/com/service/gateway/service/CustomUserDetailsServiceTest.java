package com.service.gateway.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class CustomUserDetailsServiceTest {
    @InjectMocks
    private CustomUserDetailsService service;

    @ParameterizedTest
    @CsvSource({
            "user, user, password, USER",
            "admin, admin, admin, ADMIN",
            "unknown, null, null, null"
    })
    void testFindByUsername(String inputUsername, String expectedUsername, String expectedPassword, String expectedRole) {
        User user = service.findByUsername(inputUsername);

        if (user != null) {
            assertEquals(expectedUsername, user.getUsername());
            assertEquals(expectedPassword, user.getPassword());
            assertEquals(1, user.getAuthorities().size());
            assertEquals("ROLE_" + expectedRole, user.getAuthorities().iterator().next().getAuthority());
        } else {
            assertNull(user);
        }
    }
}