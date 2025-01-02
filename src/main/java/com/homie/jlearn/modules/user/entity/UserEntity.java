package com.homie.jlearn.modules.user.entity;

import com.homie.jlearn.common.AuditingEntity;
import com.homie.jlearn.constants.RoleType;
import com.homie.jlearn.constants.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "user")
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends AuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    @Size(max = 25)
    @Column(name = "username", nullable = false, unique = true, length = 25)
    private String username;

    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Email
    @NotNull
    @Size(max = 320)
    @Column(name = "email", nullable = false, unique = true, length = 320)
    private String email;

    @Enumerated(EnumType.ORDINAL)
    @NotNull
    @Column(name = "status", nullable = false)
    private UserStatus status;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "role", nullable = false)
    private RoleType role;

    @Size(max = 100)
    @Column(name = "activation_key", length = 100)
    private String activationKey;
}
