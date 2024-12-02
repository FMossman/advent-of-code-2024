package com.mossman.AoC2024.days;

import com.mossman.AoC2024.domain.Report;
import com.mossman.AoC2024.services.DayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Day2Service extends DayService {

    private static final Logger logger = LoggerFactory.getLogger(Day2Service.class);

    @Value("${adventofcode.day2.part1.url}")
    private String day2part1Url;

    private ArrayList<Report> reports;

    public Day2Service()
    {
        this.reports = new ArrayList<>();
    }
    @Override
    public int getDayNumber() {
        return 2;
    }

    @Override
    public String calculateSolution(int part) {
        getPart1Input();
        parseInput();
        switch (part) {
            case 1:
                logger.debug("Calculating solution for Day 2 Part 1.");
                int part1result = countSafeReports();
                return "Solution for day 2 part 1: " + part1result;
            case 2:
                logger.debug("Calculating solution for Day 2 Part 2.");
                int part2result = countSafeReportsWithOneFlaw();
                return "Solution for day 2 part 2: " + part2result;
            default:
                throw new IllegalArgumentException("Invalid part: " + part);
        }
    }

    @Override
    public String getPart1Input() {
        return fetchInputData(day2part1Url, getDayNumber(), 1);
    }


    @Override
    public String getPart2Input() {
        return null;
    }

    private void parseInput()
    {
        String filePath = "input/day2part1.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Process each line
                processLine(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processLine(String line)
    {
        String[] parts = line.trim().split("\\s+");
        Report report = new Report();
        List<Integer> levels = new ArrayList<>();
        for (String part: parts) {
            int number = Integer.parseInt(part);
            levels.add(number);
        }
        report.setLevels(levels);
        logger.debug("Report saved: {}", report.toString());
        reports.add(report);
    }

    private int countSafeReports()
    {
       // int noOfLevels = reports.get(0).getNoOfLevels();
        for (Report report : reports) {
            report.setSafe(false);
            report.setSafe(checkIfSafe(report.getLevels()));
        }
        return (int) reports.stream().filter(Report::isSafe).count();
    }

    private int countSafeReportsWithOneFlaw()
    {
        // int noOfLevels = reports.get(0).getNoOfLevels();
        for (Report report : reports) {
            report.setSafe(false);
            report.setSafe(checkIfSafeWithOneFlaw(report.getLevels()));
        }
        return (int) reports.stream().filter(Report::isSafe).count();
    }

    private boolean checkIfSafe(List<Integer> levels)
    {
        boolean isIncreasing = levels.get(0) < levels.get(1);

        if (isIncreasing)
        {
            return checkIncreasingSafe(levels);
        } else {
            return checkDecreasingSafe(levels);
        }

    }

    private boolean checkIfSafeWithOneFlaw(List<Integer> levels)
    {
        boolean isIncreasing = levels.get(0) < levels.get(1);
        boolean safe = false;
        if (isIncreasing)
        {
            safe = checkIncreasingSafeWithOneFlaw(levels, 0);
            if (safe) {
                return safe;
            } else {
                return checkDecreasingSafeWithOneFlaw(levels, 0);
            }
        } else {
            safe = checkDecreasingSafeWithOneFlaw(levels, 0);
            if (safe) {
                return safe;
            } else {
                return checkIncreasingSafeWithOneFlaw(levels, 0);
            }
        }

    }

    private boolean checkDecreasingSafe(List<Integer> levels) {
        if (levels.size() <= 1)
        {
            return true;
        }
        else if ((levels.get(0) - levels.get(1) < 1 || (levels.get(0) - levels.get(1) > 3)))
        {
            return false;
        }
        else{
            levels.remove(0);
            return checkDecreasingSafe(levels);
        }
    }

    public boolean checkDecreasingSafeWithOneFlaw(List<Integer> levels, int flaws) {
        //base case
        if (levels.size() <= 1) {
            return true;
        }

        for (int i = 0; i < levels.size() - 1; i++) {
            if (levels.get(i) - levels.get(i + 1) < 1 || levels.get(i) - levels.get(i + 1) > 3) {
                if (flaws == 0) {
                    flaws++;
                    // Try removing the current element and check the rest
                    List<Integer> newLevels = new ArrayList<>(levels);
                    newLevels.remove(i);
                    if (checkDecreasingSafeWithOneFlaw(newLevels, flaws)) {
                        return true;
                    }
                    // Try removing the next element and check the rest
                    newLevels = new ArrayList<>(levels);
                    newLevels.remove(i + 1);
                    return checkDecreasingSafeWithOneFlaw(newLevels, flaws);
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkIncreasingSafe(List<Integer> levels) {
        if (levels.size() <= 1)
        {
            return true;
        }
        else if ((levels.get(1) - levels.get(0) < 1 || (levels.get(1) - levels.get(0) > 3)))
        {
            return false;
        }
        else{
            levels.remove(0);
            return checkIncreasingSafe(levels);
        }
    }

    public boolean checkIncreasingSafeWithOneFlaw(List<Integer> levels, int flaws) {
        if (levels.size() <= 1) {
            return true;
        }
        for (int i = 0; i < levels.size() - 1; i++) {
            if (levels.get(i + 1) - levels.get(i) < 1 || levels.get(i + 1) - levels.get(i) > 3) {
                if (flaws == 0) {
                    flaws++;
                    // Try removing the current element and check the rest
                    List<Integer> newLevels = new ArrayList<>(levels);
                    newLevels.remove(i);
                    if (checkIncreasingSafeWithOneFlaw(newLevels, flaws)) {
                        return true;
                    }
                    // Try removing the next element and check the rest
                    newLevels = new ArrayList<>(levels);
                    newLevels.remove(i + 1);
                    return checkIncreasingSafeWithOneFlaw(newLevels, flaws);
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
