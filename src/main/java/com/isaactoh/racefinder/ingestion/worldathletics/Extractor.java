package com.isaactoh.racefinder.ingestion.worldathletics;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Extractor {
    private static final Pattern COUNTRY_PATTERN = Pattern.compile("\\(([A-Z]{3})\\)$");

    public static String extractCountry(String venue) {
        Matcher matcher = COUNTRY_PATTERN.matcher(venue);
        return matcher.find() ? matcher.group(1) : null;
    }
}
