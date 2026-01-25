package ru.yandex.practicum.sleeptracker.supportingfunctions;

import ru.yandex.practicum.sleeptracker.SleepSession;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class SumTotalDurationOfSleepSessions implements Function<List<SleepSession>, Long> {
    @Override
    public Long apply(List<SleepSession> sleepSessions) {
        return sleepSessions.stream()
                .map(session -> Duration.between(session.getSleepSessionStart(), session.getSleepSessionEnd()))
                .mapToLong(Duration::toMinutes)
                .sum();
    }
}
