package com.metar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@SpringBootApplication
public class MetarApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetarApplication.class, args);
	}


}
