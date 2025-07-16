package com.monevia.bookstore.user_service;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createUser(
            @RequestBody
            @Valid CreateUserDTO createUserDTO) {
        String newUserId = userService.createUser(createUserDTO);
        Map<String, String> responseBody = Map.of("user_id", newUserId);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @PatchMapping("/{user_id}/update")
    public ResponseEntity<Map<String, String>> updateUser(
            @PathVariable("user_id") String userId,
            @RequestBody @Valid UpdateUserDTO updateUserDTO) {
        userService.updateUser(userId, updateUserDTO);
        Map<String, String> response = Map.of(
                "message", UserConstants.USER_UPDATED,
                "user_id", userId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{user_id}/delete")
    public ResponseEntity<Map<String, String>> deleteBook(
            @PathVariable("user_id") String userId) {
        String message = userService.deleteUser(userId);
        return ResponseEntity.ok(Map.of("message", message));
    }
}