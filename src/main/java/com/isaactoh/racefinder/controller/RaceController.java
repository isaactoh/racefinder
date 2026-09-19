package com.isaactoh.racefinder.controller;

import com.isaactoh.racefinder.race.Race;
import com.isaactoh.racefinder.race.RaceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class RaceController {

    private final RaceService raceService;

    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @GetMapping
    public List<Race> getRaces() {
        return raceService.getRaces();
    }

    @GetMapping("/{id}")
    public Race getRace(@PathVariable Integer id) {
        return raceService.getRace(id);
    }
}
