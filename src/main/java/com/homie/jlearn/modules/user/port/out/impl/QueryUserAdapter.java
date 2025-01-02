package com.homie.jlearn.modules.user.port.out.impl;

import com.homie.jlearn.modules.user.User;
import com.homie.jlearn.modules.user.UserRepository;
import com.homie.jlearn.modules.user.port.UserCriteria;
import com.homie.jlearn.modules.user.port.out.QueryUserPort;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class QueryUserAdapter implements QueryUserPort {

    private final UserRepository userRepository;

    public QueryUserAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByCriteria(UserCriteria Criteria) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public List<User> findListByCriteria(UserCriteria Criteria) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<User> findPageCriteria(UserCriteria Criteria, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }
}
