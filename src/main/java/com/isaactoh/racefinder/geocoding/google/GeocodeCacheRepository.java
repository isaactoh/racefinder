package com.isaactoh.racefinder.geocoding.google;

import com.isaactoh.racefinder.geocoding.GeocodeCache;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeocodeCacheRepository
        extends JpaRepository<GeocodeCache, String> {
}
