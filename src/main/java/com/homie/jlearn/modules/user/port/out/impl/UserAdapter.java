package com.homie.jlearn.modules.user.port.out.impl;

import com.homie.jlearn.modules.user.User;
import com.homie.jlearn.modules.user.UserRepository;
import com.homie.jlearn.modules.user.mapper.UserMapper;
import com.homie.jlearn.modules.user.port.out.UserPort;
import org.springframework.stereotype.Component;

@Component
public class UserAdapter implements UserPort {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserAdapter(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User save(User user) {
        return userMapper.toDomain(userRepository.save(userMapper.toEntity(user)));
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
}
