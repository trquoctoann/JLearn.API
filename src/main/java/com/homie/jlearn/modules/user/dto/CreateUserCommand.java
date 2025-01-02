package com.homie.jlearn.modules.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import lombok.Data;

@Data
public class CreateUserCommand implements Serializable {

    @NotNull
    @Size(max = 25)
    private String username;

    @NotNull
    private String password;

    private String firstName;

    private String lastName;

    @Email
    @NotNull
    @Size(max = 320)
    private String email;
}
