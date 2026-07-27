package com.isaactoh.racefinder.geocoding.google.dto;

import java.util.List;

public record GoogleGeocodeResponse(
        List<Result> results,
        GoogleGeocodeStatus status,
        String errorMessage
) {
    public record Result(Geometry geometry) {}

    public record Geometry(Location location) {}

    public record Location(double lat, double lng) {}
}