package ru.yandex.practicum.sleeptracker.analiticalfunctions;

import ru.yandex.practicum.sleeptracker.SleepSession;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class MaxSleepSessionFinder implements Function<List<SleepSession>, Long> {
    @Override
    public Long apply(List<SleepSession> sleepSessions) {
        return sleepSessions.stream()
                .map(session -> Duration.between(session.getSleepSessionStart(), session.getSleepSessionEnd()))
                .max(Comparator.comparingLong(Duration::toMinutes))
                .orElse(Duration.ZERO)
                .toMinutes();
    }
}
