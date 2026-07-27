package com.isaactoh.racefinder.geocoding.google;

import com.isaactoh.racefinder.geocoding.GeocodingClient;
import com.isaactoh.racefinder.geocoding.dto.Coordinates;
import com.isaactoh.racefinder.exception.LocationNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GoogleGeocodingClient implements GeocodingClient {

    private final GoogleApiClient googleApiClient;

    public GoogleGeocodingClient(GoogleApiClient googleApiClient) {
        this.googleApiClient = googleApiClient;
    }

    @Override
    public Coordinates geocode(String location) {
        var response = googleApiClient.geocode(location);

        if (response.results().isEmpty()) {
            throw new LocationNotFoundException(location);
        }

        var result = response.results().get(0);
        var coordinates = result.geometry().location();

        return new Coordinates(coordinates.lat(), coordinates.lng());
    }
}