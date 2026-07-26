package com.isaactoh.racefinder.geocoding;

import com.isaactoh.racefinder.geocoding.dto.Coordinates;
import org.springframework.stereotype.Service;

@Service
public class GeocodingService {

    private final GeocodingClient geocodingClient;

    public GeocodingService(GeocodingClient geocodingClient) {
        this.geocodingClient = geocodingClient;
    }

    public Coordinates geocode(String location) {
        return geocodingClient.geocode(location);
    }
}