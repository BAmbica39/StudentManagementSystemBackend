package com.demo.security;

import com.demo.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain)throws ServletException, IOException 
    {
        final String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) 
        {
            jwtToken = authHeader.substring(7); // Remove "Bearer " prefix
            try 
            {
                username = jwtUtil.extractUsername(jwtToken);
                System.out.println("JWT Token: " + jwtToken);
                System.out.println("Extracted Username: " + username);
            } 
            catch (Exception e) 
            {
                System.out.println("Invalid JWT token: " + e.getMessage());
            }
        } 
        else 
        {
            System.out.println("Missing or malformed Authorization header");
        }

        // Authenticate only if username was extracted and not already authenticated
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) 
        {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtUtil.validateToken(jwtToken, userDetails)) 
            {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
                System.out.println("User authenticated: " + username);
            }
            else 
            {
                System.out.println("JWT token validation failed for user: " + username);
            }
        }

        filterChain.doFilter(request, response);
    }

    // Skip filter for authentication endpoints
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) 
    {
        String path = request.getServletPath();
        return path.startsWith("/api/auth/");
    }
}
