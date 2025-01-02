package com.homie.jlearn.modules.user.port.in;

import com.homie.jlearn.modules.user.User;
import com.homie.jlearn.modules.user.dto.CreateUserCommand;
import com.homie.jlearn.modules.user.dto.UpdateUserCommand;

public interface UserUseCase {
    User create(CreateUserCommand command);

    User update(UpdateUserCommand command);

    void deleteById(String id);
}
