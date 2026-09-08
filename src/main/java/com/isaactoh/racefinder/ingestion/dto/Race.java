package com.isaactoh.racefinder.ingestion.dto;

import com.isaactoh.racefinder.geocoding.dto.Coordinates;

import java.time.Instant;

public record Race(
        Integer id,
        String name,
        String startDate,
        String endDate,
        String area,
        String country,
        String venue,
        String discipline,
        String events,
        String website,
        Coordinates coordinates,
        Instant cachedAt
) {}
