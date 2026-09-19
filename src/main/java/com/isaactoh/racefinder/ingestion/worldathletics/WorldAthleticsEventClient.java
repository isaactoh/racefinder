package com.isaactoh.racefinder.ingestion.worldathletics;

import com.isaactoh.racefinder.geocoding.GeocodingService;
import com.isaactoh.racefinder.geocoding.dto.Coordinates;
import com.isaactoh.racefinder.race.RaceRepository;
import com.isaactoh.racefinder.race.Race;
import com.isaactoh.racefinder.ingestion.IngestionClient;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;

@Component
public class WorldAthleticsEventClient implements IngestionClient {

    private final WorldAthleticsApiClient worldAthleticsApiClient;
    private final GeocodingService geocodingService;
    private final RaceRepository raceRepository;

    public WorldAthleticsEventClient(
            WorldAthleticsApiClient worldAthleticsApiClient,
            GeocodingService geocodingService,
            RaceRepository raceRepository
    ) {
        this.worldAthleticsApiClient = worldAthleticsApiClient;
        this.geocodingService = geocodingService;
        this.raceRepository = raceRepository;
    }

    public void ingest(String startDate, String endDate) {

        var response = worldAthleticsApiClient.ingest(startDate, endDate);

        for (var r : response.data().getCalendarEvents().results()) {

            // TODO: don't continue if TTL expired
            if (raceRepository.existsById(r.id())) {
                continue;
            }

            String country = Extractor.extractCountry(r.venue());
            Coordinates coordinates = geocodingService.geocode(r.venue());

            // TODO: make second API call for events offered + race website
            // TODO: extract list of events from men's and women's lists, merge

            Race race = new Race(
                r.id(),
                r.name(),
                LocalDate.parse(r.startDate()),
                LocalDate.parse(r.endDate()),
                r.area(),
                country,
                r.venue(),
                r.disciplines(),
                null, // TODO: replace with events from second call
                null, // TODO: replace with website from second call
                coordinates.latitude(),
                coordinates.longitude(),
                Instant.now()
            );

            raceRepository.save(race);
        }
    }
}
