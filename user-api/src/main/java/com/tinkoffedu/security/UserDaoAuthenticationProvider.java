package com.tinkoffedu.security;

import com.tinkoffedu.service.UserFailedLoginService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDaoAuthenticationProvider extends DaoAuthenticationProvider {

    private final UserFailedLoginService userFailedLoginService;

    public UserDaoAuthenticationProvider(
        UserAuthDetailsService userDetailsService,
        UserAuthAttemptChecker userAuthAttemptChecker,
        PasswordEncoder passwordEncoder,
        UserFailedLoginService userFailedLoginService
    ) {
        this.userFailedLoginService = userFailedLoginService;
        setPasswordEncoder(passwordEncoder);
        setUserDetailsService(userDetailsService);
        setPreAuthenticationChecks(userAuthAttemptChecker);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            return super.authenticate(authentication);
        } catch (BadCredentialsException bce) {
            userFailedLoginService.logFailedUserAttempt((String) authentication.getPrincipal());
            throw bce;
        }
    }

}
