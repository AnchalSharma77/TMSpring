package com.example.TM;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class TmApplication {
	public static void main(String[] args) {
		SpringApplication.run(TmApplication.class, args);
		
	}

}

