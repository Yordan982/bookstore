package com.monevia.bookstore.user_service;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

@Data
@NoArgsConstructor
@Entity
@DynamicUpdate
public class User {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 36, nullable = false, unique = true)
    private String id;

    @NotBlank(message = UserConstants.NAME_IS_REQUIRED)
    @Size(max = 50, message = UserConstants.NAME_MAX_LENGTH)
    private String name;

    @NotBlank(message = UserConstants.EMAIL_IS_REQUIRED)
    @Email(message = UserConstants.EMAIL_IS_INVALID,
            regexp = UserConstants.EMAIL_VALIDATION_REGEX)
    private String email;

    @NotBlank(message = UserConstants.PASSWORD_IS_REQUIRED)
    private String password;

    @NotBlank(message = UserConstants.ADDRESS_IS_REQUIRED)
    @Size(max = 80, message = UserConstants.ADDRESS_MAX_LENGTH)
    private String address;


    public User(String name, String email, String password, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
    }
}