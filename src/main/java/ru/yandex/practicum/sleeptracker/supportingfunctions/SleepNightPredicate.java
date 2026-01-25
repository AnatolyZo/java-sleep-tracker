package ru.yandex.practicum.sleeptracker.supportingfunctions;

import ru.yandex.practicum.sleeptracker.SleepSession;

import java.util.function.Predicate;

public class SleepNightPredicate implements Predicate<SleepSession> {
    SleepSessionsEndMidnight setMidnightForSessionsEnd = new SleepSessionsEndMidnight();
    SleepSessionsEndSixAM setSixAMForSessionsEnd = new SleepSessionsEndSixAM();
    SleepSessionsStartMidnight setMidnightForSessionsStart = new SleepSessionsStartMidnight();

    //Функция, проверяющая засчитывать ли сессию сна как ночную (с 0 до 6):
    //либо начало сессии сна находится в промежутке от 0 до 6 текущей даты,
    //либо если конец сессии сна приходится после полуночи следующего дня относительно начала сессии сна
    @Override
    public boolean test(SleepSession sleepSession) {
        return (sleepSession.getSleepSessionStart().isAfter(setMidnightForSessionsEnd.apply(sleepSession))
                && sleepSession.getSleepSessionStart().isBefore(setSixAMForSessionsEnd.apply(sleepSession)))
                || sleepSession.getSleepSessionEnd().isAfter(setMidnightForSessionsStart.apply(sleepSession).plusDays(1));
    }
}
