package ru.yandex.practicum.sleeptracker.analiticalfunctions;

import ru.yandex.practicum.sleeptracker.SleepSession;
import ru.yandex.practicum.sleeptracker.supportingfunctions.SleepNightsCounter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.List;
import java.util.function.Function;

public class SleeplessNightsFinder implements Function<List<SleepSession>, Long> {
    @Override
    public Long apply(List<SleepSession> sleepSessions) {
        int numberOfSleepSessions = sleepSessions.size();

        //Определение дат начала первой сессии сна и конца последней сессии сна для подсчета общего количества ночей
        LocalDate startDayDate = sleepSessions.getFirst().getSleepSessionStart().toLocalDate();
        LocalDate endDayDate = sleepSessions.get(numberOfSleepSessions - 1).getSleepSessionEnd().toLocalDate();

        //Определение момента начала первой сессии сна для выполнения условия,
        //по которому предшествующая ночь должна учитываться, если начало до полудня
        LocalDateTime firstSessionStart = sleepSessions.getFirst().getSleepSessionStart();

        long totalNights = Period.between(startDayDate, endDayDate).getDays();
        SleepNightsCounter countSleepNights = new SleepNightsCounter();

        //Добавляем 1 к количеству бессонных ночей, если начало первой сессии в промежутке с 6 до 12
        if (firstSessionStart.isBefore(LocalDateTime.of(firstSessionStart.toLocalDate(), LocalTime.NOON)) && firstSessionStart.isAfter(LocalDateTime.of(firstSessionStart.toLocalDate(), LocalTime.MIDNIGHT.plusHours(6)))) {
            return totalNights - countSleepNights.apply(sleepSessions).get().getSleepNightsCounter() + 1;
        }

        return totalNights - countSleepNights.apply(sleepSessions).get().getSleepNightsCounter();
    }
}
