package com.ftn.sbnz.service.services;

public class NormalizationService {

    public double likert(int value) {
        return (value - 1) / 4.0;
    }

    public double yesNo(boolean value) {
        return value ? 1.0 : 0.0;
    }

    public double frequency(int timesPerWeek) {
        return Math.min(1.0, timesPerWeek / 7.0);
    }

    public double invert(double v) {
        return 1.0 - v;
    }

    public double clamp(double v) {
        return Math.max(0.0, Math.min(1.0, v));
    }
}