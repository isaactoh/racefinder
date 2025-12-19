package com.isaactoh.racefinder.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeocodingService {

    @Value("${google.api.key}")
    private String googleApiKey;

    private static final String GEOCODE_URL = "https://maps.googleapis.com/maps/api/geocode/json?address=";

    public Double[] getCoordinates(String address) {
        try {
            String url = GEOCODE_URL + address.replace(" ", "+") + "&key=" + googleApiKey;

            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(url, String.class);
            JsonObject jsonResponse = JsonParser.parseString(response).getAsJsonObject();

            if (jsonResponse.get("status").getAsString().equals("OK")) {
                JsonObject location = jsonResponse.getAsJsonArray("results")
                        .get(0).getAsJsonObject().getAsJsonObject("geometry").getAsJsonObject("location");

                Double latitude = location.get("lat").getAsDouble();
                Double longitude = location.get("lng").getAsDouble();

                return new Double[]{latitude, longitude};
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}