package com.isaactoh.racefinder.ingestion.dto;

import com.isaactoh.racefinder.geocoding.dto.Coordinates;

public record Event(
        String id,
        String name,
        String startDate,
        String endDate,
        String area,
        String country,
        String venue,
        String discipline,
        Coordinates coordinates
) {}
