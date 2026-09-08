package com.isaactoh.racefinder;

import com.isaactoh.racefinder.geocoding.dto.Coordinates;
import com.isaactoh.racefinder.geocoding.GeocodingService;
import com.isaactoh.racefinder.ingestion.worldathletics.WorldAthleticsApiClient;
import com.isaactoh.racefinder.ingestion.worldathletics.dto.WorldAthleticsEventResponse;
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
    CommandLineRunner test(GeocodingService geocodingService, WorldAthleticsApiClient worldAthleticsApiClient) {
        return args -> {
            Coordinates coordinates = geocodingService.geocode("Hayward Field");
            System.out.println(coordinates);

            WorldAthleticsEventResponse waer = worldAthleticsApiClient.ingest("2026-01-01", "2026-12-31");
            var firstResult = waer.data().getCalendarEvents().results().get(0);
            System.out.println(firstResult);
        };
    }
}
