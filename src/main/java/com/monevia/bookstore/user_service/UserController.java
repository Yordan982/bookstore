package com.monevia.bookstore.user_service;

import jakarta.validation.Valid;
import org.springframework.http.MediaType;
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
        String userId = userService.createUser(createUserDTO);
        Map<String, String> response = Map.of(
                "message", UserConstants.USER_CREATED,
                "user_id", userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{user_id}/details", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetUserDTO> getUser(
            @PathVariable("user_id") String userId) {
        GetUserDTO userDTO = userService.getUserDTO(userId);
        return ResponseEntity.ok(userDTO);
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