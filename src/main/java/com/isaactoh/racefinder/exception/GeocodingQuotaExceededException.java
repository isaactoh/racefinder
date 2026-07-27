package com.isaactoh.racefinder.exception;

public class GeocodingQuotaExceededException extends GeocodingException {

    public GeocodingQuotaExceededException() {
        super("Geocoding API quota has been exceeded.");
    }

    public GeocodingQuotaExceededException(String message) {
        super(message);
    }
}