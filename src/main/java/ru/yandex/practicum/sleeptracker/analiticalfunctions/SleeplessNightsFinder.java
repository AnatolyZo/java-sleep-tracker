package ru.yandex.practicum.sleeptracker.analiticalfunctions;

import ru.yandex.practicum.sleeptracker.SleepSession;
import ru.yandex.practicum.sleeptracker.supportingfunctions.SleepNightsCounter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class SleeplessNightsFinder implements Function<List<SleepSession>, Long> {
    @Override
    public Long apply(List<SleepSession> sleepSessions) {
        int numberOfSleepSessions = sleepSessions.size();

        Function<List<SleepSession>, List<SleepSession>> sortSleepSessions = sessions -> sessions.stream()
                .sorted(Comparator.comparing(SleepSession::getSleepSessionStart))
                .toList();

        List<SleepSession> sleepSessionsSorted = sortSleepSessions.apply(sleepSessions);


        //Определение дат начала первой сессии сна и конца последней сессии сна для подсчета общего количества ночей
        LocalDate startDayDate = sleepSessionsSorted.getFirst().getSleepSessionStart().toLocalDate();
        LocalDate endDayDate = sleepSessionsSorted.get(numberOfSleepSessions - 1).getSleepSessionEnd().toLocalDate();

        //Определение момента начала первой сессии сна для выполнения условия,
        //по которому предшествующая ночь должна учитываться, если начало до полудня
        LocalDateTime firstSessionStart = sleepSessionsSorted.getFirst().getSleepSessionStart();

        long totalNights = Period.between(startDayDate, endDayDate).getDays();
        SleepNightsCounter countSleepNights = new SleepNightsCounter();
        Optional<SleepSession> totalSleepNights = countSleepNights.apply(sleepSessions);
        long totalSleeplessNights;

        if (countSleepNights.apply(sleepSessions).isPresent()) {
            totalSleeplessNights = totalNights - countSleepNights.apply(sleepSessions).get().getSleepNightsCounter();
        } else {
            totalSleeplessNights = 0L;
        }

        //Добавляем 1 к количеству бессонных ночей, если начало первой сессии в промежутке с 6 до 12
        if (firstSessionStart.isBefore(LocalDateTime.of(firstSessionStart.toLocalDate(), LocalTime.NOON)) && firstSessionStart.isAfter(LocalDateTime.of(firstSessionStart.toLocalDate(), LocalTime.MIDNIGHT.plusHours(6)))) {
            return totalSleeplessNights + 1;
        }

        return totalSleeplessNights;
    }
}
