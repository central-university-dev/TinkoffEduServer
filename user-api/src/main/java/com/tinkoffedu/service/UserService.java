package com.tinkoffedu.service;

import com.tinkoffedu.dto.user.UserRequest;
import com.tinkoffedu.dto.user.UserResponse;
import com.tinkoffedu.entity.User;
import com.tinkoffedu.exception.InvalidArgumentException;
import com.tinkoffedu.exception.NotFoundException;
import com.tinkoffedu.mapper.UserMapper;
import com.tinkoffedu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createUser(UserRequest dto) {
        validateRequest(dto);

        repository.findByEmail(dto.email()).ifPresentOrElse(
            user -> {
                throw new InvalidArgumentException("User with email %s already exists".formatted(user.getEmail()));
            },
            () -> repository.save(mapper.map(dto, passwordEncoder))
        );
    }

    @Transactional(readOnly = true)
    public UserResponse getUser(Long id) {
        var user = repository.findById(id).orElseThrow(
            () -> new NotFoundException(User.class)
        );
        return mapper.map(user);
    }

    @Transactional
    public void updateUser(Long id, UserRequest dto) {
        validateRequest(dto);

        var existingUser = repository.findById(id).orElseThrow(
            () -> new NotFoundException(User.class)
        );
        var user = mapper.map(dto)
            .setId(existingUser.getId())
            .setPassword(existingUser.getPassword())
            .setRoles(existingUser.getRoles());
        if (dto.password() != null) {
            user.setPassword(passwordEncoder.encode(dto.password()));
        }
        repository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        var existingUser = repository.findById(id).orElseThrow(
            () -> new NotFoundException(User.class)
        );
        repository.delete(existingUser);
    }

    // TODO: сделать нормальную валидацию
    private void validateRequest(UserRequest dto) {
        if (dto.email().length() > 64 || dto.firstName().length() > 64 || dto.lastName().length() > 64) {
            throw new InvalidArgumentException("email and names should contain less than 64 chars");
        }
        if (dto.phone().length() > 16) {
            throw new InvalidArgumentException("phone should contain less than 16 chars");
        }
    }

}