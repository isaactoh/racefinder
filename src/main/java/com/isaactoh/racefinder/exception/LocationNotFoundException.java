package com.isaactoh.racefinder.exception;

public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException(String location) {
        super("No coordinates found for location: " + location);
    }
}
