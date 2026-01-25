package ru.yandex.practicum.sleeptracker.coverfunctions;

import ru.yandex.practicum.sleeptracker.SleepSession;
import ru.yandex.practicum.sleeptracker.analiticalfunctions.MinSleepSessionFinder;

import java.util.List;
import java.util.function.Function;

public class MinSleepSessionFinderCover implements Function<List<SleepSession>, String> {
    MinSleepSessionFinder function = new MinSleepSessionFinder();

    @Override
    public String apply(List<SleepSession> sleepSessions) {
        return String.format("Наименьшая по продолжительности сессия сна составляет - %d минут", function.apply(sleepSessions));
    }
}
