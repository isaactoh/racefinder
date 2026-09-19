package com.isaactoh.racefinder.ingestion;

public interface IngestionClient {
    void ingest(String startDate, String endDate);
}
