package com.tinkoffedu.controller;

import com.tinkoffedu.dto.auth.AuthResponse;
import com.tinkoffedu.exception.InvalidArgumentException;
import com.tinkoffedu.security.JwtTokenUtils;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtils tokenUtils;

    @PostMapping(value = "/auth/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Login success"),
        @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public AuthResponse login(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );
            String token = tokenUtils.createToken(authentication.getName());
            return new AuthResponse(token);
        } catch (BadCredentialsException e) {
            throw new InvalidArgumentException("invalid username or password");
        } catch (Exception e) {
            throw new InvalidArgumentException("login request is invalid " + e.getMessage());
        }
    }
}
