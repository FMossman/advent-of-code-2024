package com.mossman.AoC2024.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;



public abstract class DayService {
    private static final Logger logger = LoggerFactory.getLogger(DayService.class);

    protected final RestTemplate restTemplate = new RestTemplate();

    @Value("${adventofcode.session.cookie}")
    protected String sessionCookie;

    public abstract int getDayNumber();
    public abstract String calculateSolution(int part);
    public abstract String getPart1Input();
    public abstract String getPart2Input();

    protected String fetchInputData(String url, int day, int part) {
        logger.debug("Fetching data from URL: {}", url);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", "session=" + sessionCookie);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        String data = response.getBody();
        String filename = "input/day" + day + "part" + part + ".txt";
        logger.debug("Saving data to file: {}", filename);
        writeToFile(filename, data);
        return data;
    }

    protected void writeToFile(String fileName, String data) {
        try {
            Files.createDirectories(Paths.get("input"));
            Files.write(Paths.get(fileName), data.getBytes());
            logger.debug("Data written to file: {}", fileName);
        } catch (IOException e) {
            logger.error("Error writing to file: {}", fileName, e);
            e.printStackTrace();
        }
    }
}
