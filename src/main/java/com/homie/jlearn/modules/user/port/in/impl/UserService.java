package com.homie.jlearn.modules.user.port.in.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.homie.jlearn.common.StringFilter;
import com.homie.jlearn.constants.UserStatus;
import com.homie.jlearn.modules.user.User;
import com.homie.jlearn.modules.user.dto.CreateUserCommand;
import com.homie.jlearn.modules.user.dto.UpdateUserCommand;
import com.homie.jlearn.modules.user.port.UserCriteria;
import com.homie.jlearn.modules.user.port.in.UserUseCase;
import com.homie.jlearn.modules.user.port.out.QueryUserPort;
import com.homie.jlearn.modules.user.port.out.UserPort;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService implements UserUseCase {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserPort userPort;

    private final QueryUserPort queryUserPort;

    private final ObjectMapper objectMapper;

    public UserService(UserPort userPort, QueryUserPort queryUserPort, ObjectMapper objectMapper) {
        this.userPort = userPort;
        this.queryUserPort = queryUserPort;
        this.objectMapper = objectMapper;
    }

    @Override
    public Optional<User> findById(String id) {
        UserCriteria criteria = new UserCriteria();

        StringFilter idFilter = new StringFilter();
        idFilter.setEq(id);
        criteria.setId(idFilter);

        return queryUserPort.findByCriteria(criteria);
    }

    @Override
    public User getById(String id) {
        return findById(id).orElseThrow(() -> new RuntimeException("Not found user with id: " + id));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        UserCriteria criteria = new UserCriteria();

        StringFilter usernameFilter = new StringFilter();
        usernameFilter.setEq(username);
        criteria.setUsername(usernameFilter);

        return queryUserPort.findByCriteria(criteria);
    }

    @Override
    public User getByUsername(String username) {
        return findByUsername(username).orElseThrow(() -> new RuntimeException("Not found user with username: " + username));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        UserCriteria criteria = new UserCriteria();

        StringFilter emailFilter = new StringFilter();
        emailFilter.setEq(email);
        criteria.setEmail(emailFilter);

        return queryUserPort.findByCriteria(criteria);
    }

    @Override
    public User getByEmail(String email) {
        return findByEmail(email).orElseThrow(() -> new RuntimeException("Not found user with email: " + email));
    }

    @Override
    public Optional<User> findByCriteria(UserCriteria criteria) {
        return queryUserPort.findByCriteria(criteria);
    }

    @Override
    public List<User> findListByCriteria(UserCriteria criteria) {
        return queryUserPort.findListByCriteria(criteria);
    }

    @Override
    public Page<User> findPageCriteria(UserCriteria criteria, Pageable pageable) {
        return queryUserPort.findPageCriteria(criteria, pageable);
    }

    @Override
    public void create(CreateUserCommand command) {
        log.debug("Creating user: {}", command);
        log.debug("Created user: {}", command);
    }

    @Override
    public void update(UpdateUserCommand command) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteById(String id) {
        log.debug("Deleting user has id: {}", id);

        User user = getById(id);
        user.setStatus(UserStatus.DELETED);
        userPort.save(user);

        log.debug("Deleted user has id: {}", id);
    }
}
