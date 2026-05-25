package com.jobtracker.backend.controller;

import com.jobtracker.backend.model.User;
import com.jobtracker.backend.service.UserService;
import com.jobtracker.backend.dto.CreateUserRequest;
import com.jobtracker.backend.dto.UserDTO;
import com.jobtracker.backend.dto.UpdateNavbarPositionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody CreateUserRequest request) {
        User user = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserDTO.fromEntity(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return userService.getUserById(id)
            .map(user -> ResponseEntity.ok(UserDTO.fromEntity(user)))
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/navbar-position")
    public ResponseEntity<UserDTO> updateNavbarPosition(@PathVariable Long id, @Valid @RequestBody UpdateNavbarPositionRequest request) {
        User user = userService.updateNavbarPosition(id, request.getPosition());
        return ResponseEntity.ok(UserDTO.fromEntity(user));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers()
            .stream()
            .map(UserDTO::fromEntity)
            .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }
}
