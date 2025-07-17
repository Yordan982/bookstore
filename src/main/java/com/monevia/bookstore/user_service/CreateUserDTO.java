package com.monevia.bookstore.user_service;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUserDTO {
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
}