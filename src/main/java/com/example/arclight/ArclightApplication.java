package com.example.arclight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })

public class ArclightApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArclightApplication.class, args);
	}
}
