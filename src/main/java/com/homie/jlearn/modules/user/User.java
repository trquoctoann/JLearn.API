package com.homie.jlearn.modules.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.homie.jlearn.constants.RoleType;
import com.homie.jlearn.constants.UserStatus;
import java.io.Serializable;
import lombok.Data;

@Data
public class User implements Serializable {

    private String id;

    private String username;

    @JsonIgnore
    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private UserStatus status;

    private RoleType role;

    @JsonIgnore
    private String activationKey;
}
