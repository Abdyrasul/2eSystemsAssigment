package com.metar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.Locale;

@SpringBootApplication
public class MetarApplication {

	public static void main(String[] args) {
		for (String arg : args){
			String[] split = arg.split("=");
			if(split.length>1){
				String key = split[0];
				String value = split[1];
				switch (key){
					case "scheduling.enabled":{
						if(value.toLowerCase(Locale.ROOT).equals("true") || value.toLowerCase(Locale.ROOT).equals("false")){
							System.setProperty("scheduling.enabled", value);
						}
					}
					case "scheduling.fixedDelay": {
						System.setProperty("scheduling.fixedDelay", value);
						break;
					}
				}

			}
		}
		SpringApplication.run(MetarApplication.class, args);
	}


}
