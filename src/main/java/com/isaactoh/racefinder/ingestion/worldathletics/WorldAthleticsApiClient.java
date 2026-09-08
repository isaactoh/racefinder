package com.isaactoh.racefinder.ingestion.worldathletics;

import com.isaactoh.racefinder.ingestion.worldathletics.dto.WorldAthleticsConfig;
import com.isaactoh.racefinder.ingestion.worldathletics.dto.WorldAthleticsEventResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

@Component
public class WorldAthleticsApiClient {
    private final RestClient restClient;
    private final ObjectMapper objectMapper;
    private final WorldAthleticsConfig config;

    public WorldAthleticsApiClient(
        @Qualifier("worldAthleticsRestClient") RestClient restClient,
        ObjectMapper objectMapper,
        WorldAthleticsConfig config
    ) {
        this.restClient = restClient;
        this.objectMapper = objectMapper;
        this.config = config;
    }

    public WorldAthleticsEventResponse ingest(String startDate, String endDate) {
        Map<String, Object> variables = Map.of(
                "startDate", startDate,
                "endDate", endDate
        );

        Map<String, Object> request = Map.of(
            "operationName", "getCalendarEvents",
            "query", config.query(),
            "variables", variables
        );

        String response = restClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-api-key", config.apiKey())
                .body(request)
                .retrieve()
                .body(String.class);

        return objectMapper.readValue(response, WorldAthleticsEventResponse.class);
    }
}
