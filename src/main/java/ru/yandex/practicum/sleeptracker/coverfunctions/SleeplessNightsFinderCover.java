package ru.yandex.practicum.sleeptracker.coverfunctions;

import ru.yandex.practicum.sleeptracker.SleepSession;
import ru.yandex.practicum.sleeptracker.analiticalfunctions.SleeplessNightsFinder;

import java.util.List;
import java.util.function.Function;

public class SleeplessNightsFinderCover implements Function<List<SleepSession>, String> {
    SleeplessNightsFinder function = new SleeplessNightsFinder();

    @Override
    public String apply(List<SleepSession> sleepSessions) {
        return String.format("Количество бессонных ночей - %d", function.apply(sleepSessions));
    }
}
