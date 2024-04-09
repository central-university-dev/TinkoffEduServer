package com.tinkoffedu.controller;

import com.tinkoffedu.dto.UserAuthDetails;
import com.tinkoffedu.dto.auth.AuthRequest;
import com.tinkoffedu.dto.auth.AuthResponse;
import com.tinkoffedu.dto.auth.RefreshTokenRequest;
import com.tinkoffedu.dto.auth.RefreshTokenResponse;
import com.tinkoffedu.endpoints.AuthApi;
import com.tinkoffedu.entity.UserRefreshToken;
import com.tinkoffedu.exception.InvalidArgumentException;
import com.tinkoffedu.service.UserRefreshTokenService;
import com.tinkoffedu.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final AuthenticationManager authenticationManager;
    private final UserRefreshTokenService userRefreshTokenService;
    private final JwtTokenUtils tokenUtils;

    @Override
    public AuthResponse login(AuthRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
            );
            UserAuthDetails userDetails = (UserAuthDetails) authentication.getPrincipal();
            String accessToken = tokenUtils.createToken(userDetails.getEmail());
            UserRefreshToken refreshToken = userRefreshTokenService.createRefreshToken(userDetails.getId());
            return new AuthResponse(accessToken, refreshToken.getRefreshToken());
        } catch (BadCredentialsException e) {
            throw new InvalidArgumentException("invalid username or password");
        } catch (Exception e) {
            throw new InvalidArgumentException("login request is invalid; " + e.getMessage());
        }
    }

    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {
        UserRefreshToken refreshToken = userRefreshTokenService.updateRefreshToken(request.refreshToken());
        String accessToken = tokenUtils.createToken(refreshToken.getUser().getEmail());
        return new RefreshTokenResponse(accessToken,  refreshToken.getRefreshToken());
    }
}
