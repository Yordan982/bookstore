package com.monevia.bookstore.user_service;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateUserDTO {
    @NotBlank(message = UserConstants.NAME_IS_REQUIRED)
    @Size(max = 50, message = UserConstants.NAME_MAX_LENGTH)
    private String name;

    @NotBlank(message = UserConstants.EMAIL_IS_REQUIRED)
    @Email(message = UserConstants.EMAIL_IS_INVALID,
            regexp = UserConstants.EMAIL_VALIDATION_REGEX)
    private String email;

    @NotBlank(message = UserConstants.ADDRESS_IS_REQUIRED)
    @Size(max = 80, message = UserConstants.ADDRESS_MAX_LENGTH)
    private String address;

    public UpdateUserDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public UpdateUserDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UpdateUserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UpdateUserDTO setAddress(String address) {
        this.address = address;
        return this;
    }
}