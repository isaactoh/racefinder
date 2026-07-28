package com.isaactoh.racefinder.geocoding;

import com.isaactoh.racefinder.geocoding.dto.Coordinates;
import com.isaactoh.racefinder.geocoding.google.GeocodeCacheRepository;
import org.springframework.stereotype.Service;

@Service
public class GeocodingService {

    private final GeocodingClient geocodingClient;
    private final GeocodeCacheRepository geocodeCacheRepository;

    public GeocodingService(GeocodingClient geocodingClient, GeocodeCacheRepository geocodeCacheRepository) {
        this.geocodingClient = geocodingClient;
        this.geocodeCacheRepository = geocodeCacheRepository;
    }

    public Coordinates geocode(String location) {
        String key = location.trim().toLowerCase();

        return geocodeCacheRepository.findById(key)
                .map(cache -> new Coordinates(cache.getLatitude(), cache.getLongitude()))
                .orElseGet(() -> {
                    Coordinates coordinates = geocodingClient.geocode(location);

                    geocodeCacheRepository.save(
                            GeocodeCache.builder()
                                    .location(key)
                                    .latitude(coordinates.latitude())
                                    .longitude(coordinates.longitude())
                                    .build());

                    return coordinates;
                });

    }
}