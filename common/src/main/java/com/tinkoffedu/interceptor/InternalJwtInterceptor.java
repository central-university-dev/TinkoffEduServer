package com.tinkoffedu.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InternalJwtInterceptor implements RequestInterceptor {

    private static final String TOKEN_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    private final InternalJwtTokenUtils jwtTokenUtils;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.removeHeader(TOKEN_HEADER);
        requestTemplate.header(TOKEN_HEADER, TOKEN_PREFIX + jwtTokenUtils.createToken());
    }
}
