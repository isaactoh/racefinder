package com.isaactoh.racefinder.service;

import com.isaactoh.racefinder.dto.Coordinates;
import com.isaactoh.racefinder.dto.GoogleGeocodeResponse;
import com.isaactoh.racefinder.exception.LocationNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class GeocodingService {

    private final RestClient restClient;
    private final String apiKey;

    public GeocodingService(RestClient restClient, @Value("${google.maps.api.key}") String apiKey) {
        this.restClient = restClient;
        this.apiKey = apiKey;
    }

    public Coordinates geocode(String location) {
        GoogleGeocodeResponse response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host("maps.googleapis.com")
                        .path("/maps/api/geocode/json")
                        .queryParam("address", location)
                        .queryParam("key", apiKey)
                        .build())
                .retrieve()
                .body(GoogleGeocodeResponse.class);

        if (response == null || response.results() == null || response.results().isEmpty()) {
            throw new LocationNotFoundException(location);
        }

        var loc = response.results().get(0).geometry().location();

        return new Coordinates(loc.lat(), loc.lng());
    }
}
