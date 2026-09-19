package com.isaactoh.racefinder.race;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Race {

    @Id
    private Integer id;

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String area;
    private String country;
    private String venue;
    private String discipline;
    private String events;
    private String website;
    private Double latitude;
    private Double longitude;
    private Instant cachedAt;
}
