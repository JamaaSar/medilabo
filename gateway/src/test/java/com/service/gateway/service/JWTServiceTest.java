package com.service.gateway.service;

import com.service.gateway.exception.JwtSecurityException;
import io.jsonwebtoken.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JWTServiceTest {

    @InjectMocks
    private JWTService jwtService;
    private final String secretKey = "78e4afbb7876442519391676c6197ba21c6b95d0929255b10ad47fa88832197d";
    private User user;

    @BeforeEach
    public void setUp() {
        user = mock(User.class);
    }

    @Test
    public void testGenerateToken() {
        String token = jwtService.generateToken(user);
        assertNotNull(token);
        assertTrue(jwtService.validateToken(token));
    }

    @Test
    public void testValidateToken() {
        String validToken = jwtService.generateToken(user);
        assertTrue(jwtService.validateToken(validToken));

        String invalidToken = validToken.substring(1);
        assertFalse(jwtService.validateToken(invalidToken));
    }

    @Test
    public void testGetClaims() throws JwtSecurityException {
        when(user.getUsername()).thenReturn("testUser");
        String token = jwtService.generateToken(user);
        String bearerToken = "Bearer " + token;
        Claims claims = jwtService.getClaims(bearerToken);
        assertNotNull(claims);
        assertEquals("testUser", claims.getSubject());
    }

    @Test
    public void testGetClaimsInvalidToken() {
        String invalidBearerToken = "Bearer invalidToken";

        JwtSecurityException exception = assertThrows(JwtSecurityException.class, () -> {
            jwtService.getClaims(invalidBearerToken);
        });

        assertTrue(exception.getMessage().contains("Invalid JWT token"));
    }

    @Test
    public void testGetClaimsExpiredToken() throws InterruptedException {

        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() - 3900000)) // 1
                // hour expiry
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        Thread.sleep(30);
        String bearerToken = "Bearer " + token;

        JwtSecurityException exception = assertThrows(JwtSecurityException.class, () -> {
            jwtService.getClaims(bearerToken);
        });

        assertTrue(exception.getMessage().contains("Expired JWT token"));
    }


    @Test
    public void testExtractToken() throws JwtSecurityException {
        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() - 3900000)) // 1
                // hour expiry
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        String bearerToken = "Beare Beare " + token;
        // Act & Assert
        assertThrows(JwtSecurityException.class, () -> {
            jwtService.getClaims(bearerToken);
        });
    }

}
