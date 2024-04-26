package com.tinkoffedu.filter;

import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Collections;

public class InternalJwtAuthorizationToken extends PreAuthenticatedAuthenticationToken {

    public InternalJwtAuthorizationToken(String principal, String credentials) {
        super(principal, credentials, Collections.emptyList());
    }

}
