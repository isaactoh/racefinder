package com.isaactoh.racefinder;

import com.isaactoh.racefinder.ingestion.worldathletics.WorldAthleticsEventClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RacefinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(RacefinderApplication.class, args);
	}

    @Bean
    CommandLineRunner test(WorldAthleticsEventClient worldAthleticsEventClient) {
        return args -> {
            worldAthleticsEventClient.ingest("2026-01-01", "2026-12-31");
        };
    }
}
