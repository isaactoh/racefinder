package com.isaactoh.racefinder.ingestion;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;

@Service
public class IngestionService {
    private final IngestionClient ingestionClient;

    public IngestionService(IngestionClient ingestionClient) {
        this.ingestionClient = ingestionClient;
    }

    public void ingest() {
        int currentYear = Year.now().getValue();

        String startDate = LocalDate.of(currentYear, 1, 1).toString();
        String endDate = LocalDate.of(currentYear + 1, 12, 31).toString();

        ingestionClient.ingest(startDate, endDate);
    }
}
