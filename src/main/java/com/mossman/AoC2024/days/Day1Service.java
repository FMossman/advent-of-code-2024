package com.mossman.AoC2024.days;

import com.mossman.AoC2024.services.DayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;

@Component
public class Day1Service extends DayService
{

    private static final Logger logger = LoggerFactory.getLogger(Day1Service.class);
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${adventofcode.day1.part1.url}")
    private String day1part1Url;

    @Value("${adventofcode.day1.part2.url}")
    private String day1part2Url;

    @Value("${adventofcode.session.cookie}")
    private String sessionCookie;

    private List<Integer> firstList = new ArrayList<>();

    private List<Integer> secondList = new ArrayList<>();


    @Override
    public int getDayNumber() {
        return 1;
    }

    @Override
    public String calculateSolution(int part) {
        firstList.clear();
        secondList.clear();
        getPart1Input();
        parseInput();
        orderLists();
        switch (part) {
            case 1:
                logger.debug("Calculating solution for Day 1 Part 1.");
                int part1result = calculateSumOfDifferences();
                return "Solution for day 1 part 1: " + part1result;
            case 2:
                logger.debug("Calculating solution for Day 1 Part 2.");
                int part2result = calculateSimilarities();
                return "Solution for day 1 part 2: " + part2result;
            default:
                throw new IllegalArgumentException("Invalid part: " + part);
        }
    }

    @Override
    public String getPart1Input() {
        return fetchInputData(day1part1Url, 1, 1);
    }

    @Override
    public String getPart2Input() {
        return fetchInputData(day1part2Url, 1,2);
    }





    private void parseInput()
    {
        String filePath = "input/day1part1.txt";

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
        if (parts.length == 2) {
            int firstNumber = Integer.parseInt(parts[0]);
            int secondNumber = Integer.parseInt(parts[1]);
            firstList.add(firstNumber);
            secondList.add(secondNumber);
        }
    }

    private void orderLists()
    {
        firstList.sort(Integer::compareTo);
        secondList.sort(Integer::compareTo);
    }

    public int calculateSumOfDifferences() {
        return IntStream.range(0, Math.min(firstList.size(), secondList.size()))
                .map(i -> Math.abs(firstList.get(i) - secondList.get(i)))
                .sum();
    }


    public int calculateSimilarities()
    {
        int total = 0;

        //Create a frequency map for secondList
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int number : secondList) {
            frequencyMap.put(number, frequencyMap.getOrDefault(number, 0) + 1);
        }

        //Iterate through firstList and calculate total similarities
        for (int number : firstList)
        {
            if (frequencyMap.containsKey(number)) {
                total += number * frequencyMap.get(number);
            }
        }
        return total;
    }
}

