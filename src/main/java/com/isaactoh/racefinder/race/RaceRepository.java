package com.isaactoh.racefinder.race;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RaceRepository extends JpaRepository<Race, Integer> {

    List<Race> findByStartDateLessThanEqualOrEndDateGreaterThanEqual(
            LocalDate startDate,
            LocalDate endDate
    );
}
