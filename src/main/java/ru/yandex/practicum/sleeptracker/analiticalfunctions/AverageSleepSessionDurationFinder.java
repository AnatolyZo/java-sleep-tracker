package ru.yandex.practicum.sleeptracker.analiticalfunctions;

import ru.yandex.practicum.sleeptracker.SleepSession;
import ru.yandex.practicum.sleeptracker.supportingfunctions.SumTotalDurationOfSleepSessions;

import java.util.List;
import java.util.function.Function;

public class AverageSleepSessionDurationFinder implements Function<List<SleepSession>, Long> {
    @Override
    public Long apply(List<SleepSession> sleepSessions) {
        long averageSleepSessionDuration;
        int numberOfSleepSessions = sleepSessions.size();
        SumTotalDurationOfSleepSessions sumTotalDurationOfSleepSessions = new SumTotalDurationOfSleepSessions();

        try {
            averageSleepSessionDuration = sumTotalDurationOfSleepSessions.apply(sleepSessions) / numberOfSleepSessions;
        } catch (ArithmeticException e) {
            averageSleepSessionDuration = 0L;
        }
        return averageSleepSessionDuration;
    }
}