package com.monevia.bookstore.user_service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public String createUser(CreateUserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException(UserConstants.EMAIL_IS_TAKEN);
        }
        User user = new User(userDTO.getName(), userDTO.getEmail().toLowerCase(), encodePassword(userDTO.getPassword()), userDTO.getAddress(), Role.STANDARD);
        userRepository.save(user);
        return user.getId();
    }

    public GetUserDTO getUserDTO(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new IllegalArgumentException(UserConstants.USER_NOT_FOUND));
        return new GetUserDTO(user.getName(), user.getEmail(), user.getAddress());
    }

    public void updateUser(String userId, UpdateUserDTO updateUserDTO) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new IllegalArgumentException(UserConstants.USER_NOT_FOUND));
        if (updateUserDTO.getPassword() != null) {
            updateUserDTO.setPassword(encodePassword(updateUserDTO.getPassword()));
        }
        if (updateUserDTO.getEmail() != null) {
            if (userRepository.existsByEmailAndIdNot(updateUserDTO.getEmail(), user.getId())) {
                throw new IllegalArgumentException(UserConstants.EMAIL_IS_TAKEN);
            }
            updateUserDTO.setEmail(updateUserDTO.getEmail().toLowerCase());
        }
        userMapper.updateUserFromDTO(updateUserDTO, user);
        userRepository.save(user);
    }

    public String deleteUser(String userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException(UserConstants.USER_NOT_FOUND);
        }
        userRepository.deleteById(userId);
        return UserConstants.USER_DELETED;
    }

    public String encodePassword(String password) {
        if (!password.matches(UserConstants.PASSWORD_VALIDATION_REGEX)) {
            throw new IllegalArgumentException(UserConstants.PASSWORD_INVALID);
        }
        return passwordEncoder.encode(password);
    }
}