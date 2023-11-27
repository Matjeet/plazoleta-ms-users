package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.handler.ITokenHandler;
import com.pragma.powerup.domain.model.Role;
import com.pragma.powerup.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TokenHandler implements ITokenHandler {

    private static final String ACCESS_TOKEN_SECRET = "fa52f4e13bc9557eb253c02ed25d0ecaf9c37a3e";
    private static final int ACCESS_TOKEN_VALIDITY_SECONDS = 10800;
    @Override
    public String createToken(String user, String email, List<String> roles) {

        int expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        Map<String, Object> extra = new HashMap<>();
        extra.put("name", user);
        extra.put("role", roles);

        return Jwts
                .builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }

    public UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
        Claims claims;

        try {
            claims = Jwts
                    .parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }
        catch (Exception e) {
            return null;
        }

        String email =claims.getSubject();
        List<String> role = claims.get("role", AbstractList.class);

        return new UsernamePasswordAuthenticationToken(
                email,
                null,
                role.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
        );
    }

}
