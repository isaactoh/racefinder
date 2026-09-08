package com.isaactoh.racefinder.ingestion.worldathletics;

import com.isaactoh.racefinder.ingestion.dto.Race;
import com.isaactoh.racefinder.ingestion.IngestionClient;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class WorldAthleticsEventClient implements IngestionClient {

    private WorldAthleticsApiClient worldAthleticsApiClient;

    private static final Pattern COUNTRY_PATTERN = Pattern.compile("\\(([A-Z]{3})\\)$");

    public WorldAthleticsEventClient(WorldAthleticsApiClient worldAthleticsApiClient) {
        this.worldAthleticsApiClient = worldAthleticsApiClient;
    }

    public List<Race> ingest(String startDate, String endDate) {
        var response = worldAthleticsApiClient.ingest(startDate, endDate);

        List<Race> races = new ArrayList<>();

        for (var r : response.data().getCalendarEvents().results()) {

            // TODO: continue if in cache and TTL not expired

            String venue = r.venue();

            Matcher matcher = COUNTRY_PATTERN.matcher(venue);
            String country = matcher.find() ? matcher.group(1) : null;

            // TODO: geocode coordinates

            // TODO: make second API call for events offered + race website
            // TODO: extract list of events from men's and women's lists, merge

            races.add(new Race(
                    r.id(),
                    r.name(),
                    r.startDate(),
                    r.endDate(),
                    r.area(),
                    country,
                    venue,
                    r.disciplines(),
                    null, // TODO: replace with event list from second call
                    null, // TODO: replace with website from second call
                    null, // TODO: replace with geocoded coordinates
                    Instant.now()
            ));
        }

        return races;
    }
}
