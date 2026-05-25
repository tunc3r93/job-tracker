package com.jobtracker.backend.dto;

import com.jobtracker.backend.model.User;
import com.jobtracker.backend.model.NavbarPosition;

public class UserDTO {
    private Long id;
    private String email;
    private String name;
    private NavbarPosition navbarPosition;

    public static UserDTO fromEntity(User user) {
        UserDTO dto = new UserDTO();
        dto.id = user.getId();
        dto.email = user.getEmail();
        dto.name = user.getName();
        dto.navbarPosition = user.getNavbarPosition();
        return dto;
    }

    public User toEntity() {
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setNavbarPosition(navbarPosition);
        return user;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getName() { return name; }
    public NavbarPosition getNavbarPosition() { return navbarPosition; }
}
