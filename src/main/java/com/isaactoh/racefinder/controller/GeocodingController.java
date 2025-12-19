package com.isaactoh.racefinder.controller;

import com.isaactoh.racefinder.service.GeocodingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/geocode")
public class GeocodingController {

    private final GeocodingService geocodingService;

    public GeocodingController(GeocodingService geocodingService) {
        this.geocodingService = geocodingService;
    }

    @GetMapping
    public Map<String, Object> geocode(@RequestParam String address) {
        Double[] coords = geocodingService.getCoordinates(address);

        Map<String, Object> response = new HashMap<>();
        response.put("address", address);

        if (coords != null) {
            response.put("lat", coords[0]);
            response.put("lng", coords[1]);
        } else {
            response.put("error", "Unable to geocode address");
        }

        return response;
    }
}
