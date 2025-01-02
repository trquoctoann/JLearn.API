package com.homie.jlearn.modules.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateUserCommand extends CreateUserCommand {

    @NotNull
    private String id;
}
