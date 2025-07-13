package com.monevia.bookstore.user_service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String createUser(CreateUserDTO userDTO) {
        User user = new User(userDTO.getName(), userDTO.getEmail().toLowerCase(), userDTO.getAddress());
        userRepository.save(user);
        return user.getId();
    }

    public void updateUser(String userId, UpdateUserDTO updateUserDTO) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        UserConstants.USER_NOT_FOUND));
        user.setName(updateUserDTO.getName());
        userRepository.save(user);
    }
}