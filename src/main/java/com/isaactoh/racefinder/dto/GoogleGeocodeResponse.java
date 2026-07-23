package com.isaactoh.racefinder.dto;

import java.util.List;

public record GoogleGeocodeResponse(List<Result> results) {
    public record Result(Geometry geometry) {}

    public record Geometry(Location location) {}

    public record Location(double lat, double lng) {}
}