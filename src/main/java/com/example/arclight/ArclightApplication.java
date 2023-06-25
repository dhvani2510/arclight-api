package com.example.arclight;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class ArclightApplication {
	private static final Logger LOGGER= LoggerFactory.getLogger(ArclightApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ArclightApplication.class, args);
		LOGGER.info("Arclight is running");
	}
}
