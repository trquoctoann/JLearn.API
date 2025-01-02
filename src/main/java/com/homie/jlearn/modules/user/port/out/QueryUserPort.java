package com.homie.jlearn.modules.user.port.out;

import com.homie.jlearn.modules.user.User;
import com.homie.jlearn.modules.user.port.UserCriteria;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QueryUserPort {
    Optional<User> findByCriteria(UserCriteria criteria);

    List<User> findListByCriteria(UserCriteria criteria);

    Page<User> findPageCriteria(UserCriteria criteria, Pageable pageable);
}
