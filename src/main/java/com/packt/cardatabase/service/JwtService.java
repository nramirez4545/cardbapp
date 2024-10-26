package com.packt.cardatabase.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtService {
    static final long EXPIRATIONTIME = 86400000;

    static final String PREFIX = "Bearer";

    //Generate the secret key
    static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    //Generate signed JWT token

    public String getToken(String username){
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() +EXPIRATIONTIME))
                .signWith(key)
                .compact();

                return token;

    }
//Get a token from request auth header, verify token, and get username
    public String getAuthUser(HttpServletRequest request) {

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token != null) {
            String user = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token.replace(PREFIX, ""))
                    .getBody()
                    .getSubject();

            if (user != null){
                return user;
            }
        }
        return null;
    }
}
