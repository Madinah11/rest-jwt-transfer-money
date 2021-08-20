package com.example.restjwttransfermoney.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    long expireTime = 36000000;
    String securityKey = "TokenUchunMaxfiySo'z";

    public String generateToken(String username) {
        Date expireDate = new Date(System.currentTimeMillis() + expireTime);
        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, securityKey)
                .compact();
        return token;
    }

    public boolean validateToken(String token) {
        try {
            Jwts
                    .parser()
                    .setSigningKey(securityKey)
                    .parseClaimsJws(token);
            return true;

        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }

    }

    public String getUsernameFromToken(String token){
        String username = Jwts
                .parser()
                .setSigningKey(securityKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return username;
    }
}
