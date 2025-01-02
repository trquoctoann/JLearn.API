package com.homie.jlearn.modules.user.port.in.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.homie.jlearn.constants.RoleType;
import com.homie.jlearn.constants.UserStatus;
import com.homie.jlearn.modules.user.User;
import com.homie.jlearn.modules.user.dto.CreateUserCommand;
import com.homie.jlearn.modules.user.dto.UpdateUserCommand;
import com.homie.jlearn.modules.user.port.in.QueryUserUseCase;
import com.homie.jlearn.modules.user.port.in.UserUseCase;
import com.homie.jlearn.modules.user.port.out.UserPort;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService implements UserUseCase {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final QueryUserUseCase queryUserUseCase;

    private final UserPort userPort;

    private final PasswordEncoder passwordEncoder;

    private final ObjectMapper objectMapper;

    public UserService(QueryUserUseCase queryUserUseCase, UserPort userPort, PasswordEncoder passwordEncoder, ObjectMapper objectMapper) {
        this.queryUserUseCase = queryUserUseCase;
        this.userPort = userPort;
        this.passwordEncoder = passwordEncoder;
        this.objectMapper = objectMapper;
    }

    @Override
    public User create(CreateUserCommand command) {
        log.debug("Creating user: {}", command);
        validateCreateCommand(command);

        User user = objectMapper.convertValue(command, User.class);
        user.setPassword(passwordEncoder.encode(command.getPassword()));
        user.setStatus(UserStatus.ACTIVE);
        user.setRole(RoleType.USER);

        user = userPort.save(user);
        log.debug("Created user: {}", command);

        return user;
    }

    @Override
    public User update(UpdateUserCommand command) {
        log.debug("Updating user: {}", command);
        User existUser = queryUserUseCase.getById(command.getId());

        User user = objectMapper.convertValue(command, User.class);
        user.setUsername(existUser.getUsername());
        user.setPassword(existUser.getPassword());
        user.setEmail(existUser.getEmail());
        user.setStatus(existUser.getStatus());
        user.setRole(existUser.getRole());
        user.setActivationKey(existUser.getActivationKey());

        user = userPort.save(user);
        log.debug("Updated user: {}", command);

        return user;
    }

    @Override
    public void deleteById(String id) {
        log.debug("Deleting user has id: {}", id);

        User user = queryUserUseCase.getById(id);
        user.setStatus(UserStatus.DELETED);
        userPort.save(user);

        log.debug("Deleted user has id: {}", id);
    }

    private void validateCreateCommand(CreateUserCommand command) {
        if (command.getUsername() != null) {
            Optional<User> optionalUser = queryUserUseCase.findByUsername(command.getUsername());
            if (optionalUser.isPresent()) {
                throw new RuntimeException("Duplicate username");
            }
        }
        if (command.getEmail() != null) {
            Optional<User> optionalUser = queryUserUseCase.findByEmail(command.getEmail());
            if (optionalUser.isPresent()) {
                throw new RuntimeException("Duplicate email");
            }
        }
    }
}
