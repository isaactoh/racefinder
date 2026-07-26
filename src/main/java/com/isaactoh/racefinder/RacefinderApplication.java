package com.isaactoh.racefinder;

import com.isaactoh.racefinder.geocoding.dto.Coordinates;
import com.isaactoh.racefinder.geocoding.GeocodingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RacefinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(RacefinderApplication.class, args);
	}

    @Bean
    CommandLineRunner test(GeocodingService geocodingService) {
        return args -> {
            Coordinates coordinates = geocodingService.geocode("Hayward Field");
            System.out.println(coordinates);
        };
    }
}
