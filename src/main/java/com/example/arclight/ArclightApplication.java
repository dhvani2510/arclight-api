package com.example.arclight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })

public class ArclightApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArclightApplication.class, args);
	}
}
