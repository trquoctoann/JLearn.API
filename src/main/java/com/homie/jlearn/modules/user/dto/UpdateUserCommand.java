package com.homie.jlearn.modules.user.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateUserCommand extends CreateUserCommand {

    private String id;
}
