package com.tinkoffedu.controller;

import static com.tinkoffedu.utils.UserPermissionUtils.getAllowedAuthorities;

import com.tinkoffedu.dto.UserAuthDetails;
import com.tinkoffedu.dto.auth.AuthRequest;
import com.tinkoffedu.dto.auth.AuthResponse;
import com.tinkoffedu.dto.auth.RefreshTokenRequest;
import com.tinkoffedu.dto.auth.RefreshTokenResponse;
import com.tinkoffedu.endpoints.AuthApi;
import com.tinkoffedu.entity.User;
import com.tinkoffedu.entity.UserRefreshToken;
import com.tinkoffedu.exception.InvalidArgumentException;
import com.tinkoffedu.mapper.UserAuthDetailsMapper;
import com.tinkoffedu.service.UserRefreshTokenService;
import com.tinkoffedu.utils.UserJwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final AuthenticationManager authenticationManager;
    private final UserRefreshTokenService userRefreshTokenService;
    private final UserAuthDetailsMapper userAuthDetailsMapper;
    private final UserJwtTokenUtils tokenUtils;

    @Override
    public AuthResponse login(AuthRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email().toLowerCase(), request.password())
            );
            UserAuthDetails userDetails = (UserAuthDetails) authentication.getPrincipal();
            String accessToken = tokenUtils.createToken(userDetails);
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
        User user = refreshToken.getUser();
        String accessToken = tokenUtils.createToken(
            userAuthDetailsMapper.map(user, getAllowedAuthorities(user.getRoles()))
        );
        return new RefreshTokenResponse(accessToken,  refreshToken.getRefreshToken());
    }
}
