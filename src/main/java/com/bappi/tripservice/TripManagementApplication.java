package com.bappi.tripservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TripManagementApplication {

	public static void main(String[] args) {
		System.out.println("Application is running");
		SpringApplication.run(TripManagementApplication.class, args);
	}

}
