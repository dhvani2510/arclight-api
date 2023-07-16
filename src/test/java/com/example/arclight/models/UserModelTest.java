package com.example.arclight.models;

import com.example.arclight.entities.File;
import com.example.arclight.entities.User;
import com.example.arclight.entities.datatypes.LanguageOption;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserModelTest {

    @Test
    void testConstructorWithAge() {
        // Arrange
        Long id = 1L;
        String firstName = "A";
        String lastName = "B";
        LocalDate birthDate = LocalDate.of(2000,1,1);
        String email = "A.B@example.com";
        Integer age = 23;
        Long imageId = 123L;

        // Act
        UserModel userModel = new UserModel(id, firstName, lastName, birthDate, email, age, imageId);

        // Assert
        assertEquals(id, userModel.id);
        assertEquals(firstName, userModel.firstName);
        assertEquals(lastName, userModel.lastName);
        assertEquals(birthDate, userModel.birthDate);
        assertEquals(email, userModel.email);
        assertEquals(age, userModel.age);
        assertEquals("/api/v1/files/123", userModel.getImage());
        assertNull(userModel.getSecondaryLanguage());
    }

    @Test
    void testConstructorWithoutAge() {
        // Arrange
        Long id = 1L;
        String firstName = "A";
        String lastName = "B";
        LocalDate birthDate = LocalDate.of(2000,1,1);
        String email = "A.B@example.com";
        Long imageId = 123L;

        // Act
        UserModel userModel = new UserModel(id, firstName, lastName, birthDate, email, null, imageId);

        // Assert
        assertEquals(id, userModel.id);
        assertEquals(firstName, userModel.firstName);
        assertEquals(lastName, userModel.lastName);
        assertEquals(birthDate, userModel.birthDate);
        assertEquals(email, userModel.email);
        assertEquals(23, userModel.age);
        assertEquals("/api/v1/files/123", userModel.getImage());
        assertNull(userModel.getSecondaryLanguage());
    }

    @Test
    void testUserConstructor() {
        // Arrange
        User user = new User();
        user.Id = 1L;
        user.firstName = "A";
        user.lastName = "B";
        user.birthDate = LocalDate.of(2000,1,1);
        user.setImage(new File());
        user.setSecondaryLanguage(LanguageOption.English);

        // Act
        UserModel userModel = new UserModel(user);

        // Assert
        assertEquals(user.Id, userModel.id);
        assertEquals(user.firstName, userModel.firstName);
        assertEquals(user.lastName, userModel.lastName);
        assertEquals(user.birthDate, userModel.birthDate);
        assertEquals(user.getEmail(), userModel.email);
        assertEquals(23, userModel.age);
        assertEquals(null, userModel.getImage());
        assertEquals(LanguageOption.English, userModel.getSecondaryLanguage());
    }
}