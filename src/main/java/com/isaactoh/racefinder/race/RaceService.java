package com.isaactoh.racefinder.race;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

@Service
public class RaceService {

    private final RaceRepository raceRepository;
    int currentYear = Year.now().getValue();
    private final LocalDate startDate = LocalDate.of(currentYear, 1, 1);
    private final LocalDate endDate = LocalDate.of(currentYear + 1, 12, 31);

    public RaceService(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    public List<Race> getRaces() {
        return raceRepository.findByStartDateLessThanEqualOrEndDateGreaterThanEqual(
             startDate, endDate
        );
    }

    public Race getRace(Integer id) {
        return raceRepository.findById(id).orElseThrow(() -> new RuntimeException("Race not found"));
    }
}
