package com.homie.jlearn.modules.user.port.out;

import com.homie.jlearn.modules.user.User;

public interface UserPort {
    User save(User user);

    void deleteById(String id);
}
