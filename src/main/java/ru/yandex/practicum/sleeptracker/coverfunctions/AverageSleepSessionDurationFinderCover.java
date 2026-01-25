package ru.yandex.practicum.sleeptracker.coverfunctions;

import ru.yandex.practicum.sleeptracker.SleepSession;
import ru.yandex.practicum.sleeptracker.analiticalfunctions.AverageSleepSessionDurationFinder;

import java.util.List;
import java.util.function.Function;

public class AverageSleepSessionDurationFinderCover implements Function<List<SleepSession>, String> {
    AverageSleepSessionDurationFinder function = new AverageSleepSessionDurationFinder();

    @Override
    public String apply(List<SleepSession> sleepSessions) {
        return String.format("Средняя продолжительность сессий сна составляет - %d минут", function.apply(sleepSessions));
    }
}
