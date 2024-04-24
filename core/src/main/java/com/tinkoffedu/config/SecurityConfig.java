package com.tinkoffedu.config;

import static org.springframework.security.config.Customizer.withDefaults;

import com.tinkoffedu.filter.InternalJwtAuthorizationFilter;
import com.tinkoffedu.security.UserAuthDetailsService;
import com.tinkoffedu.security.UserDaoAuthenticationProvider;
import com.tinkoffedu.security.UserJwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserAuthDetailsService userAuthDetailsService;
    private final UserDaoAuthenticationProvider userDaoAuthenticationProvider;
    private final InternalJwtAuthorizationFilter internalJwtAuthorizationFilter;
    private final UserJwtAuthorizationFilter userJwtAuthorizationFilter;

    @Bean
    public AuthenticationManager authenticationManager(
        HttpSecurity http,
        PasswordEncoder passwordEncoder
    ) throws Exception {
        AuthenticationManagerBuilder managerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        managerBuilder.userDetailsService(userAuthDetailsService).passwordEncoder(passwordEncoder);
        return managerBuilder.build();
    }

    @Bean
    @Order(1)
    public SecurityFilterChain internalFilterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/internal/**").authenticated()
                .anyRequest().permitAll()
            )
            .httpBasic(withDefaults())
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(internalJwtAuthorizationFilter, BasicAuthenticationFilter.class)
            .csrf(AbstractHttpConfigurer::disable)
            .exceptionHandling(configurer -> configurer.authenticationEntryPoint(authEntryPoint()))
            .build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/error/**", "/swagger/**", "/v3/api-docs/**").permitAll()
                .requestMatchers("/api/auth/**", "/api/user/register").permitAll()
                .anyRequest().authenticated()
            )
            .httpBasic(withDefaults())
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(userDaoAuthenticationProvider)
            .addFilterBefore(userJwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
            .csrf(AbstractHttpConfigurer::disable)
            .exceptionHandling(configurer -> configurer.authenticationEntryPoint(authEntryPoint()))
            .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        List<String> corsAllList = List.of(CorsConfiguration.ALL);

        var corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOriginPatterns(corsAllList);
        corsConfiguration.setAllowedMethods(corsAllList);
        corsConfiguration.setAllowedHeaders(corsAllList);
        corsConfiguration.setExposedHeaders(corsAllList);

        var corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return corsConfigurationSource;
    }

    private AuthenticationEntryPoint authEntryPoint() {
        return (request, response, authException) -> {
            if (authException != null) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
            }
        };
    }
}
