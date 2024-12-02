package com.mossman.AoC2024.controllers;

import com.mossman.AoC2024.factories.DayServiceFactory;
import com.mossman.AoC2024.services.DayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

/**
 * The main REST controller for the <i>Advent Of Code 2018</i> Application.
 * It is used to handle calls to {@code /api/adventOfCode}.
 *
 * @author Michelle Fernandez Bieber
 */
@RestController
@RequestMapping("/api")
public class DayController {

    @Autowired
    private DayServiceFactory dayServiceFactory;

    @GetMapping("day/{day}/part/{part}")
    public String getSolution(@PathVariable int day, @PathVariable int part)
    {
        DayService dayService = dayServiceFactory.getService(day);
        return dayService.calculateSolution(part);
    }
}

