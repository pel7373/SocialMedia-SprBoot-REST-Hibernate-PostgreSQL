package com.example.socialmedia.controller;

import com.example.socialmedia.dto.UserDTO;
import com.example.socialmedia.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/{userId}/follow/{followerId}")
    public ResponseEntity<UserDTO> followUser(@PathVariable Long userId, @PathVariable Long followerId) {
        return ResponseEntity.ok(userService.followUser(userId, followerId));
    }
}
