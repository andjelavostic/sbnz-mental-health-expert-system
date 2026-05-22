package com.ftn.sbnz.service;

public class NormalizationService {

    public double likert(String value) {
        return switch (value.toLowerCase()) {
            case "nikad" -> 0.0;
            case "retko" -> 0.25;
            case "ponekad" -> 0.5;
            case "cesto" -> 0.75;
            case "uvek" -> 1.0;
            default -> 0.0;
        };
    }

    public double yesNo(boolean value) {
        return value ? 1.0 : 0.0;
    }

    public double frequency(int timesPerWeek) {
        if (timesPerWeek == 0) return 0;
        if (timesPerWeek <= 2) return 0.25;
        if (timesPerWeek <= 4) return 0.5;
        if (timesPerWeek <= 6) return 0.75;
        return 1.0;
    }
}