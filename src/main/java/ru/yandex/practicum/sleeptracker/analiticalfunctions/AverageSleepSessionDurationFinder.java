package ru.yandex.practicum.sleeptracker.analiticalfunctions;

import ru.yandex.practicum.sleeptracker.SleepSession;
import ru.yandex.practicum.sleeptracker.supportingfunctions.SumTotalDurationOfSleepSessions;

import java.util.List;
import java.util.function.Function;

public class AverageSleepSessionDurationFinder implements Function<List<SleepSession>, Long> {
    @Override
    public Long apply(List<SleepSession> sleepSessions) {
        SumTotalDurationOfSleepSessions sumTotalDurationOfSleepSessions = new SumTotalDurationOfSleepSessions();
        int numberOfSleepSessions = sleepSessions.size();
        return sumTotalDurationOfSleepSessions.apply(sleepSessions) / numberOfSleepSessions;
    }
}