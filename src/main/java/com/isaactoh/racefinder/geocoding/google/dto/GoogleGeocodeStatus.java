package com.isaactoh.racefinder.geocoding.google.dto;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum GoogleGeocodeStatus {
    OK,
    ZERO_RESULTS,
    INVALID_REQUEST,
    OVER_QUERY_LIMIT,
    REQUEST_DENIED,
    UNKNOWN_ERROR,

    @JsonEnumDefaultValue
    UNKNOWN
}
