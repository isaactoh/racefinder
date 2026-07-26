package com.isaactoh.racefinder.geocoding;

import com.isaactoh.racefinder.geocoding.dto.Coordinates;

public interface GeocodingClient {
    Coordinates geocode(String location);
}
