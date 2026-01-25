package ru.yandex.practicum.sleeptracker.coverfunctions;

import ru.yandex.practicum.sleeptracker.SleepSession;
import ru.yandex.practicum.sleeptracker.analiticalfunctions.MaxSleepSessionFinder;

import java.util.List;
import java.util.function.Function;

public class MaxSleepSessionFinderCover implements Function<List<SleepSession>, String> {
    MaxSleepSessionFinder function = new MaxSleepSessionFinder();

    @Override
    public String apply(List<SleepSession> sleepSessions) {
        return String.format("Наибольшая по продолжительности сессия сна составляет - %d минут", function.apply(sleepSessions));
    }
}
