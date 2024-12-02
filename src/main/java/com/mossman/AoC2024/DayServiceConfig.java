package com.mossman.AoC2024;

import com.mossman.AoC2024.services.DayService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class DayServiceConfig {

    @Autowired
    private ApplicationContext applicationContext;

    private Map<Integer, DayService> dayServices;

    @PostConstruct
    public void initializeDayServices() {
        dayServices = applicationContext.getBeansOfType(DayService.class).values().stream()
                .collect(Collectors.toMap(DayService::getDayNumber, service -> service));
    }

    @Bean
    public Map<Integer, DayService> getDayServices() {
        return dayServices;
    }
}
