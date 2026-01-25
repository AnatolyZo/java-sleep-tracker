package ru.yandex.practicum.sleeptracker.supportingfunctions;

import ru.yandex.practicum.sleeptracker.SleepSession;

import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class SleepNightsCounter implements Function<List<SleepSession>, Optional<SleepSession>> {
    SleepSessionsEndMidnight setMidnightForSessionsEnd = new SleepSessionsEndMidnight();
    SleepSessionsEndSixAM setSixAMForSessionsEnd = new SleepSessionsEndSixAM();
    SleepSessionsStartMidnight setMidnightForSessionsStart = new SleepSessionsStartMidnight();
    SleepSessionsStartSixAM setSixAMForSessionsStart = new SleepSessionsStartSixAM();
    SleepNightPredicate isSleepNight = new SleepNightPredicate();

    @Override
    public Optional<SleepSession> apply(List<SleepSession> sleepSessions) {
        return sleepSessions.stream()
                .filter(isSleepNight)
                //метод reduce() предназначен для исключения ситуаций двойного подсчета количества ночных сессий сна
                //для случая, когда конец предыдущей и начало следующей сессий приходятся на период с 0 до 6
                //если такое происходит, то из предыдущей сессии в следующую передается счетчик без изменений
                .reduce((a, b) -> {
                    if ((Period.between(a.getSleepSessionEnd().toLocalDate(), b.getSleepSessionStart().toLocalDate()).getDays() == 0)
                            && a.getSleepSessionEnd().isAfter(setMidnightForSessionsEnd.apply(a)) && a.getSleepSessionEnd().isBefore(setSixAMForSessionsEnd.apply(a))
                            && b.getSleepSessionStart().isAfter(setMidnightForSessionsStart.apply(b)) && b.getSleepSessionStart().isBefore(setSixAMForSessionsStart.apply(b))) {
                        return b.setSleepNights(a.getSleepNightsCounter());
                    } else {
                        return b.incrementSleepNights(a.getSleepNightsCounter());
                    }

                });
    }
}
