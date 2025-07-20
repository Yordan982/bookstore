package com.monevia.bookstore.user_service;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UuidGenerator;

@Data
@NoArgsConstructor
@Entity
@DynamicUpdate
public class User {
    @Id
    @UuidGenerator
    @Column(length = 36, nullable = false, unique = true)
    private String id;

    @NotBlank(message = UserConstants.NAME_IS_REQUIRED)
    @Size(max = 50, message = UserConstants.NAME_MAX_LENGTH)
    private String name;

    @Column(unique = true)
    @NotBlank(message = UserConstants.EMAIL_IS_REQUIRED)
    @Email(message = UserConstants.EMAIL_IS_INVALID,
            regexp = UserConstants.EMAIL_VALIDATION_REGEX)
    private String email;

    @NotBlank(message = UserConstants.PASSWORD_IS_REQUIRED)
    private String password;

    @NotBlank(message = UserConstants.ADDRESS_IS_REQUIRED)
    @Size(max = 80, message = UserConstants.ADDRESS_MAX_LENGTH)
    private String address;

    @NotNull(message = UserConstants.ROLE_IS_REQUIRED)
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String name, String email, String password, String address, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.role = role;
    }
}