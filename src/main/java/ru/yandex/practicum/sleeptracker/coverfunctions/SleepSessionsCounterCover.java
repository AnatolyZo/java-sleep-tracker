package ru.yandex.practicum.sleeptracker.coverfunctions;

import ru.yandex.practicum.sleeptracker.SleepSession;
import ru.yandex.practicum.sleeptracker.analiticalfunctions.SleepSessionsCounter;

import java.util.List;
import java.util.function.Function;

public class SleepSessionsCounterCover implements Function<List<SleepSession>, String> {
    SleepSessionsCounter function = new SleepSessionsCounter();

    @Override
    public String apply(List<SleepSession> sleepSessions) {
        return String.format("Количество сессий сна - %d", function.apply(sleepSessions));
    }
}
