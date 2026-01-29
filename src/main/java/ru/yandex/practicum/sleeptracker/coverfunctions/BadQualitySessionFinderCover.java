package ru.yandex.practicum.sleeptracker.coverfunctions;

import ru.yandex.practicum.sleeptracker.SleepSession;
import ru.yandex.practicum.sleeptracker.analiticalfunctions.BadQualitySessionFinder;

import java.util.List;
import java.util.function.Function;

public class BadQualitySessionFinderCover implements Function<List<SleepSession>, String> {
    BadQualitySessionFinder function = new BadQualitySessionFinder();

    @Override
    public String apply(List<SleepSession> sleepSessions) {
        return String.format("Количество сессий сна неудовлетворительного качества составляет - %d", function.apply(sleepSessions));
    }
}
