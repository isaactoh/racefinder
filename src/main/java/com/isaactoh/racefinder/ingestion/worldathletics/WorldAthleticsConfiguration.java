package com.isaactoh.racefinder.ingestion.worldathletics;

import com.isaactoh.racefinder.ingestion.worldathletics.dto.WorldAthleticsConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;

@Configuration
public class WorldAthleticsConfiguration {
    @Bean
    @Qualifier("worldAthleticsRestClient")
    public RestClient worldAthleticsRestClient(
            @Value("${world.athletics.api.url}") String url
    ) {
        return RestClient.builder()
                .baseUrl(url)
                .build();
    }

    @Bean
    public WorldAthleticsConfig worldAthleticsConfig(
            @Value("${world.athletics.api.key}")  String apiKey,
            @Value("classpath:graphql/GetCalendarEvents.graphql") Resource queryResource
    ) {
        try {
            return new WorldAthleticsConfig(
                    apiKey,
                    queryResource.getContentAsString(StandardCharsets.UTF_8)
            );
        } catch (IOException e) {
            throw new UncheckedIOException(
                    "Failed to load GraphQL query", e
            );
        }
    }
}
