package com.monevia.bookstore.user_service;

public class UserConstants {
    public static final String NAME_IS_REQUIRED = "Name is required";
    public static final String NAME_MAX_LENGTH = "Name must be {max} characters or fewer";
    public static final String EMAIL_IS_REQUIRED = "Email is required";
    public static final String EMAIL_IS_INVALID = "Email is not valid";
    public static final String EMAIL_VALIDATION_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static final String PASSWORD_VALIDATION_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{10,50}$";
    public static final String PASSWORD_ENCRYPTED_REGEX = "^\\$2[aby]\\$.{56}$";
    public static final String PASSWORD_IS_NOT_HASHED = "Password must be a valid bcrypt hash";
    public static final String PASSWORD_IS_REQUIRED = "Password is required";
    public static final String PASSWORD_INVALID = "Password must be 10-50 characters long and include at least one digit, one special character, one uppercase and one lowercase letter";
    public static final String ADDRESS_IS_REQUIRED = "Address is required";
    public static final String ADDRESS_MAX_LENGTH = "Address must be {max} characters or fewer";
    public static final String ROLE_IS_REQUIRED = "Role is required";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String USER_CREATED = "User created successfully";
    public static final String USER_UPDATED = "User updated successfully";
    public static final String USER_DELETED = "User deleted successfully";
}