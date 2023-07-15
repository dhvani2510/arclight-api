package com.example.arclight.services;

import com.example.arclight.entities.User;
import com.example.arclight.entities.datatypes.Role;
import com.example.arclight.models.UserModel;
import com.example.arclight.repositories.UserRepository;
import com.example.arclight.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
    void testGetUsers_Success() {
    	String s[] = {"aarushi@example.com", "s@example.com"};
        // Arrange
        List<User> users = new ArrayList<>();
        users.add(new User.UserBuilder("Aarushi", "Bagri")
                .setId(1L)
                .setEmail("aarushi@example.com")
                .setRole(Role.User)
                .build());
        users.add(new User.UserBuilder("S", "S") 
        		.setId(2L)
      		  	.setEmail("s@example.com") 
      		  	.setRole(Role.User) 
      		  	.build());

        when(userRepository.findAll()).thenReturn(users);

        // Act
        List<UserModel> result = userService.GetUsers();

        // Assert
        Assertions.assertEquals(users.size(), result.size());
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            UserModel userModel = result.get(i);
            Assertions.assertEquals(s[i], user.getEmail());
        }

        verify(userRepository, times(1)).findAll();
    }
    
}
