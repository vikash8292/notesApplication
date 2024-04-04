package com.notesapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.notesapp.service.UserService;


@SpringBootApplication
@EnableScheduling
public class NotesApplication {

	@Autowired
	private UserService userService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(NotesApplication.class, args);
	}
	
}
