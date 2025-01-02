package com.homie.jlearn.modules.user.port.in;

import com.homie.jlearn.modules.user.User;
import com.homie.jlearn.modules.user.dto.CreateUserCommand;
import com.homie.jlearn.modules.user.dto.UpdateUserCommand;
import com.homie.jlearn.modules.user.port.UserCriteria;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserUseCase {
    Optional<User> findById(String id);

    User getById(String id);

    Optional<User> findByUsername(String username);

    User getByUsername(String username);

    Optional<User> findByEmail(String email);

    User getByEmail(String email);

    Optional<User> findByCriteria(UserCriteria criteria);

    List<User> findListByCriteria(UserCriteria criteria);

    Page<User> findPageCriteria(UserCriteria criteria, Pageable pageable);

    void create(CreateUserCommand command);

    void update(UpdateUserCommand command);

    void deleteById(String id);
}
