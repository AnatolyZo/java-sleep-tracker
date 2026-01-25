package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;

public class SleepSession {
    private final LocalDateTime sleepSessionStart;
    private final LocalDateTime sleepSessionEnd;
    private final String sleepQuality;
    private int sleepNightsCounter = 1;

    public SleepSession(LocalDateTime sleepSessionStart, LocalDateTime getSleepSessionEnd, String sleepQuality) {
        this.sleepSessionStart = sleepSessionStart;
        this.sleepSessionEnd = getSleepSessionEnd;
        this.sleepQuality = sleepQuality;
    }

    public LocalDateTime getSleepSessionStart() {
        return sleepSessionStart;
    }

    public LocalDateTime getSleepSessionEnd() {
        return sleepSessionEnd;
    }

    public String getSleepQuality() {
        return sleepQuality;
    }

    public int getSleepNightsCounter() {
        return sleepNightsCounter;
    }

    public SleepSession setSleepNights(int sleepNightsCounter) {
        this.sleepNightsCounter = sleepNightsCounter;
        return this;
    }

    public SleepSession incrementSleepNights(int sleepNightsCounter) {
        this.sleepNightsCounter = sleepNightsCounter + 1;
        return this;
    }
}
