package ru.yandex.practicum.sleeptracker.supportingfunctions;

import ru.yandex.practicum.sleeptracker.SleepSession;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.function.Predicate;

public class SleepNightPredicate implements Predicate<SleepSession> {
    //Функция, проверяющая засчитывать ли сессию сна как ночную (с 0 до 6):
    //либо начало сессии сна находится в промежутке от 0 до 6 текущей даты,
    //либо если конец сессии сна приходится после полуночи следующего дня относительно начала сессии сна
    @Override
    public boolean test(SleepSession sleepSession) {
        LocalDateTime setMidnightForSessionsEnd = LocalDateTime.of(sleepSession.getSleepSessionEnd().toLocalDate(), LocalTime.MIDNIGHT);
        LocalDateTime setSixAMForSessionsEnd = LocalDateTime.of(sleepSession.getSleepSessionEnd().toLocalDate(), LocalTime.MIDNIGHT.plusHours(6));
        LocalDateTime setMidnightForSessionsStart = LocalDateTime.of(sleepSession.getSleepSessionStart().toLocalDate(), LocalTime.MIDNIGHT);

        return (sleepSession.getSleepSessionStart().isAfter(setMidnightForSessionsEnd)
                && sleepSession.getSleepSessionStart().isBefore(setSixAMForSessionsEnd))
                || sleepSession.getSleepSessionEnd().isAfter(setMidnightForSessionsStart.plusDays(1));
    }
}
