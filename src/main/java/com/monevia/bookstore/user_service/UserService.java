package com.monevia.bookstore.user_service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        User user = new User(userDTO.getName(), userDTO.getEmail().toLowerCase(), encodePassword(userDTO.getPassword()), userDTO.getAddress());
        userRepository.save(user);
        return user.getId();
    }

    public void updateUser(String userId, UpdateUserDTO updateUserDTO) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        UserConstants.USER_NOT_FOUND));
        if (updateUserDTO.getPassword() != null) {
            updateUserDTO.setPassword(encodePassword(updateUserDTO.getPassword()));
        }
        userMapper.updateUserFromDTO(updateUserDTO, user);
        userRepository.save(user);
    }

    public String deleteUser(String userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    UserConstants.USER_NOT_FOUND);
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