package com.isaactoh.racefinder.ingestion.worldathletics.dto;

import java.util.List;

public record WorldAthleticsEventResponse(
        Data data
) {
    public record Data(
            CalendarEvents getCalendarEvents
    ) {}

    public record CalendarEvents(
            List<Result> results
    ) {}

    public record Result(
            Integer id,
            String name,
            String startDate,
            String endDate,
            String area,
            String venue,
            String disciplines
    ) {}
}
