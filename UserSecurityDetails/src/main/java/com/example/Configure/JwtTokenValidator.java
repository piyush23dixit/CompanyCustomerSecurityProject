package com.example.Configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.Services.JwtService;
import com.example.Services.MyUserDetailsService;

@Component
public class JwtTokenValidator {
    
    @Autowired
    private JwtService jwtService;

    @Autowired
    private ApplicationContext context;

    public boolean validateAndSetAuthentication(String authHeader) {
        String token = null;
        String username = null;

        // Extract token and username from the Authorization header
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtService.extractUserName(token);
        } else {
            return false; // Invalid header format
        }

        // Load user details
        UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(username);

        // Validate token
        if (!jwtService.validateToken(token, userDetails)) {
            return false; // Token validation failed
        }

        // Set authentication in the SecurityContext
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        
        System.out.println("Authentication set for user: " + username);

        return true; // Token is valid, and user is authenticated
    }
}
