package com.example.arclight.configurations;

import com.example.arclight.entities.User;
import com.example.arclight.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

//Initializer
@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository){

        return args -> {

            User  ives=new User(
                    "Iverique",
                    "Nkayilu",
                    "nkayilu@uwindsor.ca",
                    LocalDate.of(1998,5,6)
            );
        userRepository.saveAll(List.of(ives));
        };
    };
}
