package com.mossman.AoC2024.factories;

import com.mossman.AoC2024.services.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;


@Component
public class DayServiceFactory
{
    private final Map<Integer, DayService> dayServices;

    @Autowired
    public DayServiceFactory(Map<String, DayService> dayServiceBeans) {
        this.dayServices = dayServiceBeans.values().stream()
                .collect(Collectors.toMap(DayService::getDayNumber, service -> service));
    }

    public DayService getService(int day) {
        DayService service = dayServices.get(day);
        if (service == null) {
            throw new UnsupportedOperationException("No handler found for day: " + day);
        }
        return service;
    }
}
