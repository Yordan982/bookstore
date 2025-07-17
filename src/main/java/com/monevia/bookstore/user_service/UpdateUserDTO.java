package com.monevia.bookstore.user_service;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateUserDTO {
    @Size(max = 50, message = UserConstants.NAME_MAX_LENGTH)
    private String name;

    @Email(message = UserConstants.EMAIL_IS_INVALID,
            regexp = UserConstants.EMAIL_VALIDATION_REGEX)
    private String email;

    @Pattern(
            regexp = UserConstants.PASSWORD_VALIDATION_REGEX,
            message = UserConstants.PASSWORD_INVALID)
    private String password;

    @Size(max = 80, message = UserConstants.ADDRESS_MAX_LENGTH)
    private String address;
}