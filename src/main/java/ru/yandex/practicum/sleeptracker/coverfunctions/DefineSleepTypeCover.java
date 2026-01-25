package ru.yandex.practicum.sleeptracker.coverfunctions;

import ru.yandex.practicum.sleeptracker.SleepSession;
import ru.yandex.practicum.sleeptracker.analiticalfunctions.DefineSleepType;

import java.util.List;
import java.util.function.Function;

public class DefineSleepTypeCover implements Function<List<SleepSession>, String> {
    DefineSleepType function = new DefineSleepType();

    @Override
    public String apply(List<SleepSession> sleepSessions) {
        return String.format("Ваш хронотип - %s", function.apply(sleepSessions).getType());
    }
}
