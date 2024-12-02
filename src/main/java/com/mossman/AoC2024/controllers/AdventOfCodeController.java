package com.mossman.AoC2024.controllers;

import com.mossman.AoC2024.services.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AdventOfCodeController {

    @Autowired
    private Map<Integer, DayService> dayServices;

    @GetMapping("/api/day/{day}/part1/input")
    public String getPart1Input(@PathVariable int day) {
        DayService service = dayServices.get(day);
        if (service == null) {
            throw new IllegalArgumentException("Invalid day: " + day);
        }
        return service.getPart1Input();
    }

    @GetMapping("/api/day/{day}/part2/input")
    public String getPart2Input(@PathVariable int day) {
        DayService service = dayServices.get(day);
        if (service == null) {
            throw new IllegalArgumentException("Invalid day: " + day);
        }
        return service.getPart2Input();
    }
}

