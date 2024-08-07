package com.tinkoffedu.utils;

import com.tinkoffedu.entity.Permission;
import com.tinkoffedu.entity.Role;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@UtilityClass
public class UserPermissionUtils {

    public static List<GrantedAuthority> getAllowedAuthorities(Set<Role> roles) {
        return getAuthorities(roles)
            .stream()
            .map(privilege -> (GrantedAuthority) new SimpleGrantedAuthority(privilege))
            .toList();
    }

    private static List<String> getAuthorities(Set<Role> roles) {
        Stream<String> roleNames = roles.stream()
            .map(Role::getName);
        Stream<String> privilegesNames = roles.stream()
            .map(Role::getPermissions)
            .flatMap(Set::stream)
            .map(Permission::getName);
        return Stream.concat(roleNames, privilegesNames).toList();
    }
}
