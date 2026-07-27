package com.isaactoh.racefinder.geocoding.google;

import com.isaactoh.racefinder.exception.GeocodingQuotaExceededException;
import com.isaactoh.racefinder.exception.GeocodingServiceUnavailableException;
import com.isaactoh.racefinder.exception.InvalidGeocodingRequestException;
import com.isaactoh.racefinder.geocoding.google.dto.GoogleGeocodeResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

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
        try {
            var response = restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/maps/api/geocode/json")
                            .queryParam("address", location)
                            .queryParam("key", apiKey)
                            .build())
                    .retrieve()
                    .body(GoogleGeocodeResponse.class);

            if (response == null) {
                throw new GeocodingServiceUnavailableException(
                        "Google Maps API returned an empty response.");
            }

            return switch (response.status()) {
                case OK, ZERO_RESULTS -> response;

                case INVALID_REQUEST ->
                        throw new InvalidGeocodingRequestException(
                                "Invalid geocoding request.");

                case OVER_QUERY_LIMIT ->
                        throw new GeocodingQuotaExceededException();

                case REQUEST_DENIED ->
                        throw new GeocodingServiceUnavailableException(
                                "Google Maps API request denied: " + response.errorMessage());

                case UNKNOWN_ERROR ->
                        throw new GeocodingServiceUnavailableException(
                                "Google Maps API encountered an internal error.");

                case UNKNOWN ->
                        throw new GeocodingServiceUnavailableException(
                                "Google Maps API returned an unknown status.");
            };
        } catch (RestClientException ex) {
            throw new GeocodingServiceUnavailableException(
                    "Failed to call Google Maps API.", ex);
        }
    }
}
