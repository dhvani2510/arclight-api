package com.example.arclight.configurations;

import com.example.arclight.entities.User;
import com.example.arclight.entities.datatypes.Role;
import com.example.arclight.repositories.UserRepository;
import com.example.arclight.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

//Initializer
@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder passwordEncoder){

        return args -> {

            User  ives=new User(
                    "Iverique",
                    "Nkayilu",
                    "nkayilu@uwindsor.ca",
                    LocalDate.of(1998,5,6)
            );
           var hashedPassword= passwordEncoder.encode("Abc123.");
            ives.setPassword(hashedPassword);
            ives.setRole(Role.Admin);

            var existingIves= userRepository.findByEmail(ives.email);
            if(existingIves.isEmpty()){
                userRepository.saveAll(List.of(ives));
            }
        };
    };
}
