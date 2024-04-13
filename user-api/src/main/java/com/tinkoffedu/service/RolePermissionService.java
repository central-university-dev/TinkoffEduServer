package com.tinkoffedu.service;

import com.tinkoffedu.dto.role.RolePermissionResponse;
import com.tinkoffedu.dto.role.UserRoleResponse;
import com.tinkoffedu.entity.Permission;
import com.tinkoffedu.entity.Role;
import com.tinkoffedu.entity.User;
import com.tinkoffedu.exception.InvalidArgumentException;
import com.tinkoffedu.exception.NotFoundException;
import com.tinkoffedu.repository.PermissionRepository;
import com.tinkoffedu.repository.RoleRepository;
import com.tinkoffedu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RolePermissionService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @Transactional(readOnly = true)
    public RolePermissionResponse getRolePermissions(Long roleId) {
        var role = roleRepository.findById(roleId).orElseThrow(
            () -> new NotFoundException(Role.class)
        );
        return new RolePermissionResponse(
            role.getName(),
            role.getPermissions().stream().map(Permission::getName).toList()
        );
    }

    @Transactional(readOnly = true)
    public UserRoleResponse getUserRoles(String email) {
        var user = userRepository.findByEmail(email).orElseThrow(
            () -> new NotFoundException(User.class)
        );
        return new UserRoleResponse(
            user.getEmail(),
            user.getFirstName(),
            user.getLastName(),
            user.getRoles().stream().map(Role::getName).toList()
        );
    }

    @Transactional
    public void createRole(String roleName) {
        if (!roleName.toUpperCase().startsWith("ROLE_")) {
            throw new InvalidArgumentException("Role name should starts with prefix 'ROLE_'");
        }
        roleRepository.findByName(roleName.toUpperCase()).ifPresentOrElse(
            role -> {
                throw new InvalidArgumentException("Role with name %s already exists".formatted(roleName));
            },
            () -> roleRepository.save(new Role().setName(roleName.toUpperCase()))
        );
    }

    @Transactional
    public void deleteRole(Long roleId) {
        var existingRole = roleRepository.findById(roleId).orElseThrow(
            () -> new NotFoundException(Role.class)
        );
        roleRepository.delete(existingRole);
    }

    @Transactional
    public void createPermission(String permissionName) {
        permissionRepository.findByName(permissionName).ifPresentOrElse(
            permission -> {
                throw new InvalidArgumentException("Permission with name %s already exists".formatted(permissionName));
            },
            () -> permissionRepository.save(new Permission().setName(permissionName.toUpperCase()))
        );
    }

    @Transactional
    public void deletePermission(Long permissionId) {
        var existingPermission = permissionRepository.findById(permissionId).orElseThrow(
            () -> new NotFoundException(Permission.class)
        );
        permissionRepository.delete(existingPermission);
    }

    @Transactional
    public void addRoleToUser(String email, String roleName) {
        var user = userRepository.findByEmail(email).orElseThrow(
            () -> new NotFoundException(User.class)
        );
        if (user.hasRole(roleName)) {
            return;
        }
        var addedRole = roleRepository.findByName(roleName).orElseThrow(
            () -> new NotFoundException(Role.class)
        );
        user.addRole(addedRole);
        userRepository.save(user);
    }

    @Transactional
    public void removeRoleFromUser(String email, String roleName) {
        var user = userRepository.findByEmail(email).orElseThrow(
            () -> new NotFoundException(User.class)
        );
        user.getRoles().stream()
            .filter(role -> role.getName().equalsIgnoreCase(roleName))
            .findAny()
            .ifPresent(removedRole -> {
                user.removeRole(removedRole);
                userRepository.save(user);
            });
    }

    @Transactional
    public void addPermissionToRole(Long roleId, String permissionName) {
        var role = roleRepository.findById(roleId).orElseThrow(
            () -> new NotFoundException(Role.class)
        );
        if (role.hasPermission(permissionName)) {
            return;
        }
        var addedPermission = permissionRepository.findByName(permissionName).orElseThrow(
            () -> new NotFoundException(Permission.class)
        );
        role.addPermission(addedPermission);
        roleRepository.save(role);
    }

    @Transactional
    public void removePermissionFromRole(Long roleId, String permissionName) {
        var role = roleRepository.findById(roleId).orElseThrow(
            () -> new NotFoundException(Role.class)
        );
        role.getPermissions().stream()
            .filter(permission -> permission.getName().equalsIgnoreCase(permissionName))
            .findAny()
            .ifPresent(removedPermission -> {
                role.removePermission(removedPermission);
                roleRepository.save(role);
            });
    }

}
