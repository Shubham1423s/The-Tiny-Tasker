package com.shubham.todoapp.Configuration;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.security.Key;
import java.sql.Date;

@Component
public class JwtUtil  {

    @Value("${jwt.secret}")
    private String rawSceret;

    public Key getRawSceret() {

      return   Keys.hmacShaKeyFor(rawSceret.getBytes());

    }

    public String generateToken(String userName){
        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new java.util.Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hrs
                .signWith(SignatureAlgorithm.HS256, getRawSceret())
                .compact();
    }

    public String extractUserName(String token){

        return  Jwts.parser().setSigningKey(getRawSceret()).parseClaimsJws(token).getBody()
                .getSubject();
    }

    public boolean validateToken(String token , UserDetails userDetails){
        return extractUserName(token).equals(userDetails.getUsername());
    }

}
