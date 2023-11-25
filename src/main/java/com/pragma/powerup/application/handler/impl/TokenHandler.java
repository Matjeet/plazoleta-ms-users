package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.handler.ITokenHandler;
import com.pragma.powerup.domain.model.Role;
import com.pragma.powerup.domain.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class TokenHandler implements ITokenHandler {

    private static final String ACCESS_TOKEN_SECRET = "fa52f4e13bc9557eb253c02ed25d0ecaf9c37a3e";
    private static final int ACCESS_TOKEN_VALIDITY_SECONDS = 10800;
    @Override
    public String createToken(User user, Role role) {

        int expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        Map<String, Object> extra = new HashMap<>();
        extra.put("name", user.getName());
        extra.put("lastName", user.getLastName());
        extra.put("role", role.getName());

        return Jwts
                .builder()
                .setSubject(user.getEmail())
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }
}
