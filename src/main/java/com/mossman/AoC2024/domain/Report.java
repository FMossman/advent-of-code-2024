package com.mossman.AoC2024.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Report {
    private List<Integer> levels;
    private boolean isSafe;

    @Override
    public String toString() {
        return "Report{" +
                "levels=" + levels +
                '}';
    }

    public int getNoOfLevels()
    {
        return levels.size();
    }
}
