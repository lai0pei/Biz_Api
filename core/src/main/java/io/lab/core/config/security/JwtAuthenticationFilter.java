package io.lab.core.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Value("${app.jwt.prefix}")
    private String jwtPrefix;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Prefix:"+jwtPrefix);
//        if(request.getHeader(jwtPrefix) == null) {
//            throw new ServletException("Missing jwt prefix");
//        }
        filterChain.doFilter(request, response);
        return;
    }
}
