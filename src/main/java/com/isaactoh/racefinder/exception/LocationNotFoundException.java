package com.isaactoh.racefinder.exception;

public class LocationNotFoundException extends GeocodingException {
    public LocationNotFoundException(String location) {
        super("Location not found: " + location);
    }
}
