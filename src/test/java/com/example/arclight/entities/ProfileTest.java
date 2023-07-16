package com.example.arclight.entities;

import com.example.arclight.entities.datatypes.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProfileTest {

    @Test
    void testBuilder() {
        // Arrange
        String firstName = "A";
        String lastName = "B";
        Long id = 1L;
        String email = "ab@example.com";
        String password = "password";
        Role role = Role.User;
        // Act
        Profile profile = new Profile.ProfileBuilder(firstName, lastName)
                .setId(id)
                .setEmail(email)
                .setPassword(password)
                .setRole(role)
                .build();

        // Assert
        assertEquals(email, profile.getEmail());
        assertEquals(password, profile.getPassword());
        }
}
