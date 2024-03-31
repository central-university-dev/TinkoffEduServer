package com.tinkoffedu.service;

import com.tinkoffedu.dto.user.UserRequest;
import com.tinkoffedu.dto.user.UserResponse;
import com.tinkoffedu.entity.User;
import com.tinkoffedu.exception.InvalidArgumentException;
import com.tinkoffedu.exception.NotFoundException;
import com.tinkoffedu.mapper.UserMapper;
import com.tinkoffedu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Transactional
    public void createUser(UserRequest dto) {
        validateRequest(dto);

        repository.findByEmail(dto.email()).ifPresentOrElse(
            user -> {
                throw new InvalidArgumentException(
                    "User with username %s already exists".formatted(user.getEmail())
                );
            },
            () -> repository.save(mapper.map(dto))
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
        var user = mapper.map(dto).setId(existingUser.getId());
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
