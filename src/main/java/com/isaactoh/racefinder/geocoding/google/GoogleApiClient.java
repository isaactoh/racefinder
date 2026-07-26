package com.isaactoh.racefinder.geocoding.google;

import com.isaactoh.racefinder.geocoding.google.dto.GoogleGeocodeResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class GoogleApiClient {

    private final RestClient restClient;
    private final String apiKey;

    public GoogleApiClient(
            @Qualifier("googleMapsRestClient") RestClient restClient,
            @Value("${google.maps.api.key}") String apiKey
    ) {
        this.restClient = restClient;
        this.apiKey = apiKey;
    }

    public GoogleGeocodeResponse geocode(String location) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/maps/api/geocode/json")
                        .queryParam("address", location)
                        .queryParam("key", apiKey)
                        .build())
                .retrieve()
                .body(GoogleGeocodeResponse.class);
    }
}
