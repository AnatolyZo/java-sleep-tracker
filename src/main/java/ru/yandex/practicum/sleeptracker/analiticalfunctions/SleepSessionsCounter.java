package ru.yandex.practicum.sleeptracker.analiticalfunctions;

import ru.yandex.practicum.sleeptracker.SleepSession;

import java.util.List;
import java.util.function.Function;

public class SleepSessionsCounter implements Function <List<SleepSession>, Long> {
    @Override
    public Long apply(List<SleepSession> sleepSessions) {
        return sleepSessions.stream().count();
    }
}