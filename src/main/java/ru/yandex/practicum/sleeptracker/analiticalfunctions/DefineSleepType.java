package ru.yandex.practicum.sleeptracker.analiticalfunctions;

import ru.yandex.practicum.sleeptracker.SleepSession;
import ru.yandex.practicum.sleeptracker.SleepTypes;
import ru.yandex.practicum.sleeptracker.supportingfunctions.SleepNightPredicate;
import ru.yandex.practicum.sleeptracker.supportingfunctions.SleepNightsCounter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class DefineSleepType implements Function<List<SleepSession>, SleepTypes> {
    SleepNightPredicate isSleepNight = new SleepNightPredicate();
    SleepNightsCounter countSleepNights = new SleepNightsCounter();

    @Override
    public SleepTypes apply(List<SleepSession> sleepSessions) {
        //Определение количество сессий сна с хронотипом "сова":
        Function<List<SleepSession>, Long> countOwlSleepType = sessions -> sessions.stream()
                //isSleepNight оставляет только те ночи, на которые сон приходится с 0 до 6
                .filter(isSleepNight)
                //фильтруем ночи, которые либо начинаются после полуночи (начало и конец сессии в один и тот же день)
                //и заканчиваются после 9, либо начинаются после 23 и заканчинваются после 9
                .filter(session -> (Period.between(session.getSleepSessionStart().toLocalDate(), session.getSleepSessionEnd().toLocalDate()).getDays() == 0
                        && session.getSleepSessionEnd().isAfter(LocalDateTime.of(session.getSleepSessionEnd().toLocalDate(), LocalTime.of(9, 0))))
                        || (session.getSleepSessionStart().isAfter(LocalDateTime.of(session.getSleepSessionStart().toLocalDate(), LocalTime.of(23, 0)))
                        && session.getSleepSessionEnd().isAfter(LocalDateTime.of(session.getSleepSessionEnd().toLocalDate(), LocalTime.of(9, 0)))))
                .count();

        //Определение количество сессий сна с хронотипом "жаворонок":
        Function<List<SleepSession>, Long> countLarkSleepType = sessions -> sessions.stream()
                .filter(isSleepNight)
                //фильтруем ночи, которые либо начинаются до 22 и заканчиваются до 7
                .filter(session -> Period.between(session.getSleepSessionStart().toLocalDate(), session.getSleepSessionEnd().toLocalDate()).getDays() == 1
                        && session.getSleepSessionStart().isBefore(LocalDateTime.of(session.getSleepSessionStart().toLocalDate(), LocalTime.of(22, 0)))
                        && session.getSleepSessionEnd().isBefore(LocalDateTime.of(session.getSleepSessionEnd().toLocalDate(), LocalTime.of(7, 0))))
                .count();

        Optional<SleepSession> totalSleepNights = countSleepNights.apply(sleepSessions);
        int numberOfOwlSleepType = Math.toIntExact(countOwlSleepType.apply(sleepSessions));
        int numberOfLarkSleepType = Math.toIntExact(countLarkSleepType.apply(sleepSessions));
        int numberOfPigeonSleepType = totalSleepNights.get().getSleepNightsCounter() - numberOfOwlSleepType - numberOfLarkSleepType;
        //Определяем максимальное значение из трех хронотипов
        int largestSleepType = Math.max(Math.max(numberOfOwlSleepType, numberOfLarkSleepType), numberOfPigeonSleepType);

        //Добавляем случаи, когда количество хронотипов совпадает
        if ((numberOfOwlSleepType == numberOfLarkSleepType && numberOfPigeonSleepType < numberOfOwlSleepType)
                || (numberOfOwlSleepType == numberOfPigeonSleepType && numberOfLarkSleepType < numberOfOwlSleepType)
                || (numberOfLarkSleepType == numberOfPigeonSleepType && numberOfOwlSleepType < numberOfLarkSleepType)
                || numberOfPigeonSleepType == largestSleepType) {
            return SleepTypes.PIGEON;
        } else if (numberOfOwlSleepType == largestSleepType) {
            return SleepTypes.OWL;
        }

        return SleepTypes.LARK;
    }
}