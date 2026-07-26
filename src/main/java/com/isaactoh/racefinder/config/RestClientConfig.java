package com.isaactoh.racefinder.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    @Qualifier("googleMapsRestClient")
    public RestClient googleMapsRestClient() {
        return RestClient.builder()
                .baseUrl("https://maps.googleapis.com")
                .build();
    }
}
