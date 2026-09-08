package com.isaactoh.racefinder.geocoding.google;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class GoogleConfiguration {
    @Bean
    @Qualifier("googleMapsRestClient")
    public RestClient googleMapsRestClient() {
        return RestClient.builder()
                .baseUrl("https://maps.googleapis.com")
                .build();
    }
}
