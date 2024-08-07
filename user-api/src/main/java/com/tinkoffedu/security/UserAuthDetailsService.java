package com.tinkoffedu.security;

import static com.tinkoffedu.utils.UserPermissionUtils.getAllowedAuthorities;

import com.tinkoffedu.auth.UserAuthDetails;
import com.tinkoffedu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserAuthDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(email).orElseThrow(
            () -> new UsernameNotFoundException("User with email %s not found".formatted(email))
        );
        var authorities = getAllowedAuthorities(user.getRoles());
        return UserAuthDetails.builder()
            .id(user.getId())
            .email(user.getEmail())
            .password(user.getPassword())
            .authorities(authorities)
            .build();
    }

}
