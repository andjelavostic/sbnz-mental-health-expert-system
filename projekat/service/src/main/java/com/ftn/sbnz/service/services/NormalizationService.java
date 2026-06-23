package com.ftn.sbnz.service.services;

import org.springframework.stereotype.Service;

@Service
public class NormalizationService {

    public double likert(double value) {
        return clamp((value - 1) / 4.0);
    }

    public double yesNo(double value) {
        return clamp(value);
    }

    public double frequency(double timesPerWeek) {

        if (timesPerWeek <= 0)
            return 0.0;
        if (timesPerWeek <= 2)
            return 0.25;
        if (timesPerWeek <= 4)
            return 0.5;
        if (timesPerWeek <= 6)
            return 0.75;

        return 1.0;
    }

    public double sleepHours(double hours) {

        if (hours >= 7 && hours <= 9)
            return 0.0;

        if (hours >= 6 && hours < 7)
            return 0.3;

        if (hours > 9 && hours <= 10)
            return 0.3;

        if (hours >= 5 && hours < 6)
            return 0.6;

        if (hours > 10)
            return 0.7;

        return 1.0;
    }

    public double durationDays(double days) {

        if (days <= 3)
            return 0.0;

        if (days <= 7)
            return 0.2;

        if (days <= 14)
            return 0.4;

        if (days <= 30)
            return 0.6;

        if (days <= 90)
            return 0.8;

        return 1.0; // hronično
    }

    public double invert(double v) {
        return 1.0 - v;
    }

    public double clamp(double v) {
        return Math.max(0.0, Math.min(1.0, v));
    }

}
