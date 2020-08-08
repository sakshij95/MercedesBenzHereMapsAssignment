package com.example.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableCaching
@EnableAsync
public class MercedesBenzAssignment01Application {

	public static void main(String[] args) {
		SpringApplication.run(MercedesBenzAssignment01Application.class, args);
	}

}
