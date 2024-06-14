package com.service.gateway.service;

import com.service.gateway.exception.JwtSecurityException;
import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTService {

    String secretKey = "78e4afbb7876442519391676c6197ba21c6b95d0929255b10ad47fa88832197d";

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour expiry
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }


    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Claims getClaims(final String bearerToken) throws JwtSecurityException {
        String token= extractToken(bearerToken);
        try {
            Claims claims  =
                    Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
            return claims;
        } catch (SignatureException ex) {
            throw new JwtSecurityException("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            throw new JwtSecurityException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new JwtSecurityException("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new JwtSecurityException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new JwtSecurityException("JWT claims string is empty.");
        }
    }

    private static String extractToken(String bearerToken) throws JwtSecurityException {
        String[] parts = bearerToken.split(" ");
        if (parts.length == 2 && parts[0].equalsIgnoreCase("Bearer")) {
            return parts[1];
        } else {
            throw new JwtSecurityException("Invalid Bearer token format");
        }
    }

}
