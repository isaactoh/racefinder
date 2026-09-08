package com.isaactoh.racefinder.ingestion;

import com.isaactoh.racefinder.ingestion.dto.Race;

import java.util.List;

public interface IngestionClient {
    List<Race> ingest(String startDate, String endDate);
}
