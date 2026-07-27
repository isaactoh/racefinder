package com.isaactoh.racefinder.exception;

public class GeocodingServiceUnavailableException extends GeocodingException {
    public GeocodingServiceUnavailableException(String message) {
        super(message);
    }
    public GeocodingServiceUnavailableException(String message, Throwable cause) { super(message, cause); }
}
