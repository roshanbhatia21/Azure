package com.example.exittest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "http://localhost:4200")
public class ExittestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExittestApplication.class, args);
		System.out.print("Connection Sucessfull Springboot application deployed on AZURE VM");
	}

}
