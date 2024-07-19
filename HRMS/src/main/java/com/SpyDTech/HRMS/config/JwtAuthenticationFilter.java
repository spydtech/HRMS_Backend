package com.SpyDTech.HRMS.config;


import com.SpyDTech.HRMS.service.JWTService;
import com.SpyDTech.HRMS.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JWTService jwtService;

    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        logger.info("Processing authentication for request to {}", request.getRequestURI());

        if(StringUtils.isEmpty(authHeader) || !org.apache.commons.lang3.StringUtils.startsWith(authHeader, "Bearer ")){
            logger.info("No JWT token found in the request headers");
            filterChain.doFilter(request,response);
            return;
        }
        jwt = authHeader.substring(7);
        logger.debug("Extracted JWT token: {}", jwt);
         userEmail = jwtService.extractUserName(jwt);
        logger.debug("Extracted user email from JWT: {}", userEmail);

        if(!StringUtils.isEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null){

            UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);
            logger.debug("Loaded user details for user: {}", userEmail);

            if(jwtService.isTokenValid(jwt,userDetails)){
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        userDetails,null,userDetails.getAuthorities()
                );

                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                securityContext.setAuthentication(token);
                SecurityContextHolder.setContext(securityContext);

                logger.info("User {} authenticated successfully", userEmail);
            } else
            {
                logger.warn("JWT token is invalid for user: {}", userEmail);
            }
        } else
        {

            logger.info("JWT token is either invalid or user is already authenticated");
        }

        filterChain.doFilter(request,response);
        logger.info("Authentication processing completed for request to {}", request.getRequestURI());

    }
}
