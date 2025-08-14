package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.demo")
public class StudentManagementSystemPractiseApplication {

	public static void main(String[] args) 
	{
		SpringApplication.run(StudentManagementSystemPractiseApplication.class, args);
		System.out.println("Spring Boot Main Application Started");
	}

}
