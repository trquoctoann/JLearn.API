package com.homie.jlearn.modules.user.port.out.impl;

import com.homie.jlearn.modules.user.User;
import com.homie.jlearn.modules.user.UserRepository;
import com.homie.jlearn.modules.user.entity.UserEntity;
import com.homie.jlearn.modules.user.mapper.UserMapper;
import com.homie.jlearn.modules.user.port.UserCriteria;
import com.homie.jlearn.modules.user.port.UserSpecification;
import com.homie.jlearn.modules.user.port.out.QueryUserPort;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class QueryUserAdapter implements QueryUserPort {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public QueryUserAdapter(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<User> findByCriteria(UserCriteria criteria) {
        List<UserEntity> userEntities = userRepository.findAll(new UserSpecification(criteria));
        if (CollectionUtils.isEmpty(userEntities)) {
            return Optional.empty();
        }
        if (userEntities.size() > 1) {
            throw new RuntimeException("Find more than 1 user");
        }
        return Optional.of(userEntities.get(0)).map(userEntity -> toDomain(userEntity, criteria.getFetchAttributes()));
    }

    @Override
    public List<User> findListByCriteria(UserCriteria criteria) {
        return toDomain(userRepository.findAll(new UserSpecification(criteria)), criteria.getFetchAttributes());
    }

    @Override
    public Page<User> findPageCriteria(UserCriteria criteria, Pageable pageable) {
        Page<UserEntity> userPage = userRepository.findAll(new UserSpecification(criteria), pageable);
        return new PageImpl<>(
            enrichUserDomainForPage(userPage.getContent(), criteria.getFetchAttributes()),
            pageable,
            userPage.getTotalElements()
        );
    }

    private List<User> toDomain(List<UserEntity> userEntities, Set<String> domainAttributes) {
        return userEntities.stream().map(userEntity -> toDomain(userEntity, domainAttributes)).collect(Collectors.toList());
    }

    private User toDomain(UserEntity userEntity, Set<String> domainAttributes) {
        User user = userMapper.toDomain(userEntity);
        return enrichUserDomain(user, userEntity, domainAttributes);
    }

    private User enrichUserDomain(User user, UserEntity userEntity, Set<String> domainAttributes) {
        return user;
    }

    private List<User> enrichUserDomainForPage(List<UserEntity> userEntities, Set<String> domainAttributes) {
        return toDomain(userEntities, domainAttributes);
    }
}
