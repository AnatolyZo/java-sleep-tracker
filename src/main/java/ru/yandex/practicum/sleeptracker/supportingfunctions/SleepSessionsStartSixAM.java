package ru.yandex.practicum.sleeptracker.supportingfunctions;

import ru.yandex.practicum.sleeptracker.SleepSession;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.function.Function;

public class SleepSessionsStartSixAM implements Function<SleepSession, LocalDateTime> {
    @Override
    public LocalDateTime apply(SleepSession sleepSession) {
        return LocalDateTime.of(sleepSession.getSleepSessionStart().toLocalDate(), LocalTime.MIDNIGHT.plusHours(6));
    }
}
