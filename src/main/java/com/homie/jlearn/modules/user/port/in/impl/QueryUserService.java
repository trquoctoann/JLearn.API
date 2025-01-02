package com.homie.jlearn.modules.user.port.in.impl;

import com.homie.jlearn.common.StringFilter;
import com.homie.jlearn.modules.user.User;
import com.homie.jlearn.modules.user.port.UserCriteria;
import com.homie.jlearn.modules.user.port.in.QueryUserUseCase;
import com.homie.jlearn.modules.user.port.out.QueryUserPort;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class QueryUserService implements QueryUserUseCase {

    private final QueryUserPort queryUserPort;

    public QueryUserService(QueryUserPort queryUserPort) {
        this.queryUserPort = queryUserPort;
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
}
