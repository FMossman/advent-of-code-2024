package com.mossman.AoC2024;

import com.mossman.AoC2024.days.Day2Service;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Day2ServiceTest
{
    @Test
    public void testCheckDecreasingSafeWithOneFlaw() {
        Day2Service service = new Day2Service();

        List<Integer> list1 = Arrays.asList(7, 6, 4, 2, 1);
        List<Integer> list2 = Arrays.asList(9, 7, 6, 2, 1);
        List<Integer> list3 = Arrays.asList(8, 6, 4, 4, 1);

        assertTrue(service.checkDecreasingSafeWithOneFlaw(list1, 0));
        assertFalse(service.checkDecreasingSafeWithOneFlaw(list2, 0));
        assertTrue(service.checkDecreasingSafeWithOneFlaw(list3, 0));
    }

    @Test
    public void testCheckIncreasingSafeWithOneFlaw() {
        Day2Service service = new Day2Service();

        List<Integer> list1 = Arrays.asList(1, 2, 7, 8, 9);
        List<Integer> list2 = Arrays.asList(1, 3, 2, 4, 5);
        List<Integer> list3 = Arrays.asList(1, 3, 6, 7, 9);

        assertFalse(service.checkIncreasingSafeWithOneFlaw(list1, 0));
        assertTrue(service.checkIncreasingSafeWithOneFlaw(list2, 0));
        assertTrue(service.checkIncreasingSafeWithOneFlaw(list3, 0));
    }
}

