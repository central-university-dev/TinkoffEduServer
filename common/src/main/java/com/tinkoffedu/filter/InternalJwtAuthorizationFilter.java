package com.tinkoffedu.filter;

import static com.tinkoffedu.utils.JwtUtils.isTokenExpired;
import static com.tinkoffedu.utils.JwtUtils.resolveClaims;
import static com.tinkoffedu.utils.JwtUtils.resolveToken;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class InternalJwtAuthorizationFilter extends OncePerRequestFilter {

    @Value("${security.internal.jwt.secret-key}")
    private String secretKey;

    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        if (resolveToken(request) == null) {
            filterChain.doFilter(request, response);
            return;
        }
        Claims claims = resolveClaims(request, secretKey);
        if (claims == null || isTokenExpired(claims)) {
            filterChain.doFilter(request, response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(
            new UsernamePasswordAuthenticationToken("internal-client", null)
        );
        filterChain.doFilter(request, response);
    }
}
