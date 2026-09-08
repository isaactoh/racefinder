package com.isaactoh.racefinder.ingestion;

import com.isaactoh.racefinder.ingestion.dto.Race;

import java.util.List;

public class IngestionService {
    private final IngestionClient ingestionClient;

    public IngestionService(IngestionClient ingestionClient) {
        this.ingestionClient = ingestionClient;
    }

    public List<Race> ingest(String startDate, String endDate) {
        // TODO: call ingestionClient.ingest for current year + following year
        return null;
    }
}
