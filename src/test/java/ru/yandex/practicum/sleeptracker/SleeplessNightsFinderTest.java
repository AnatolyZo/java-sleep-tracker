package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.analiticalfunctions.SleeplessNightsFinder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SleeplessNightsFinderTest {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    //Проверка при отсутствии бессонных ночей
    @Test
    public void sleeplessNightsFinderTestIfSleeplessNightsEquals0() {
        final List<String> sleepSessions = List.of("01.10.25 23:15;02.10.25 07:30;GOOD",
                "02.10.25 23:50;03.10.25 06:40;NORMAL",
                "03.10.25 14:10;03.10.25 14:50;NORMAL",
                "03.10.25 23:40;04.10.25 08:00;GOOD",
                "05.10.25 00:10;05.10.25 06:20;GOOD");

        List<SleepSession> sleepSessionList = new ArrayList<>();

        for (String str : sleepSessions) {
            LocalDateTime sleepSessionStart = LocalDateTime.parse(str.substring(0, str.indexOf(";")), FORMATTER);
            LocalDateTime sleepSessionEnd = LocalDateTime.parse(str.substring(str.indexOf(";") + 1, str.lastIndexOf(";")), FORMATTER);
            String sleepQuality = str.substring(str.lastIndexOf(";") + 1);

            sleepSessionList.add(new SleepSession(sleepSessionStart, sleepSessionEnd, sleepQuality));
        }

        SleeplessNightsFinder function = new SleeplessNightsFinder();
        assertEquals(0, function.apply(sleepSessionList));
    }

    //Проверка в случае 1 бессонной ночи
    @Test
    public void sleeplessNightsFinderTestIfSleeplessNightsEquals1() {
        final List<String> sleepSessions = List.of("01.10.25 23:15;02.10.25 07:30;GOOD",
                "02.10.25 23:50;03.10.25 06:40;NORMAL",
                "03.10.25 14:10;03.10.25 14:50;NORMAL", //бессонная ночь с 3 на 4
                "04.10.25 06:40;04.10.25 08:00;GOOD",
                "05.10.25 00:10;05.10.25 06:20;GOOD");

        List<SleepSession> sleepSessionList = new ArrayList<>();

        for (String str : sleepSessions) {
            LocalDateTime sleepSessionStart = LocalDateTime.parse(str.substring(0, str.indexOf(";")), FORMATTER);
            LocalDateTime sleepSessionEnd = LocalDateTime.parse(str.substring(str.indexOf(";") + 1, str.lastIndexOf(";")), FORMATTER);
            String sleepQuality = str.substring(str.lastIndexOf(";") + 1);

            sleepSessionList.add(new SleepSession(sleepSessionStart, sleepSessionEnd, sleepQuality));
        }

        SleeplessNightsFinder function = new SleeplessNightsFinder();
        assertEquals(1, function.apply(sleepSessionList));
    }

    //Проверка в случае 2 бессонных ночей и начале первой сессии после 12
    @Test
    public void sleeplessNightsFinderTestIfFirstSleepSessionIsAfter12PMAnd2SleeplessNights() {
        final List<String> sleepSessions = List.of("01.10.25 23:15;02.10.25 07:30;GOOD",
                "03.10.25 06:50;03.10.25 13:40;NORMAL",//бессонная ночь с 3 на 4
                "03.10.25 22:10;04.10.25 06:50;NORMAL",
                "04.10.25 13:40;04.10.25 18:00;GOOD",//бессонная ночь с 4 на 5
                "05.10.25 06:10;05.10.25 06:20;GOOD");

        List<SleepSession> sleepSessionList = new ArrayList<>();

        for (String str : sleepSessions) {
            LocalDateTime sleepSessionStart = LocalDateTime.parse(str.substring(0, str.indexOf(";")), FORMATTER);
            LocalDateTime sleepSessionEnd = LocalDateTime.parse(str.substring(str.indexOf(";") + 1, str.lastIndexOf(";")), FORMATTER);
            String sleepQuality = str.substring(str.lastIndexOf(";") + 1);

            sleepSessionList.add(new SleepSession(sleepSessionStart, sleepSessionEnd, sleepQuality));
        }

        SleeplessNightsFinder function = new SleeplessNightsFinder();
        assertEquals(2, function.apply(sleepSessionList));
    }

    //Проверка в случае 2 бессонных ночей и начале первой сессии до 12, но после 6
    @Test
    public void sleeplessNightsFinderTestIfFirstSleepSessionIsBefore12PMAndAfter6AMAnd2SleeplessNights() {
        final List<String> sleepSessions = List.of("01.10.25 11:15;02.10.25 07:30;GOOD",
                "03.10.25 06:50;03.10.25 13:40;NORMAL",//бессонная ночь с 3 на 4
                "03.10.25 22:10;04.10.25 06:50;NORMAL",
                "04.10.25 13:40;04.10.25 18:00;GOOD",//бессонная ночь с 4 на 5
                "05.10.25 06:10;05.10.25 06:20;GOOD");

        List<SleepSession> sleepSessionList = new ArrayList<>();

        for (String str : sleepSessions) {
            LocalDateTime sleepSessionStart = LocalDateTime.parse(str.substring(0, str.indexOf(";")), FORMATTER);
            LocalDateTime sleepSessionEnd = LocalDateTime.parse(str.substring(str.indexOf(";") + 1, str.lastIndexOf(";")), FORMATTER);
            String sleepQuality = str.substring(str.lastIndexOf(";") + 1);

            sleepSessionList.add(new SleepSession(sleepSessionStart, sleepSessionEnd, sleepQuality));
        }

        SleeplessNightsFinder function = new SleeplessNightsFinder();
        assertEquals(3, function.apply(sleepSessionList));
    }

    //Проверка в случае 2 бессонных ночей и начале первой сессии до 6
    @Test
    public void sleeplessNightsFinderTestIfFirstSleepSessionIsBefore12PMAndBefore6AMAnd2SleeplessNights() {
        final List<String> sleepSessions = List.of("01.10.25 05:15;02.10.25 07:30;GOOD",
                "03.10.25 06:50;03.10.25 13:40;NORMAL",//бессонная ночь с 3 на 4
                "03.10.25 22:10;04.10.25 06:50;NORMAL",
                "04.10.25 13:40;04.10.25 18:00;GOOD",//бессонная ночь с 4 на 5
                "05.10.25 06:10;05.10.25 06:20;GOOD");

        List<SleepSession> sleepSessionList = new ArrayList<>();

        for (String str : sleepSessions) {
            LocalDateTime sleepSessionStart = LocalDateTime.parse(str.substring(0, str.indexOf(";")), FORMATTER);
            LocalDateTime sleepSessionEnd = LocalDateTime.parse(str.substring(str.indexOf(";") + 1, str.lastIndexOf(";")), FORMATTER);
            String sleepQuality = str.substring(str.lastIndexOf(";") + 1);

            sleepSessionList.add(new SleepSession(sleepSessionStart, sleepSessionEnd, sleepQuality));
        }

        SleeplessNightsFinder function = new SleeplessNightsFinder();
        assertEquals(2, function.apply(sleepSessionList));
    }

    //Проверка в случае 2 бессонных ночей для случая, если они идут подряд
    @Test
    public void sleeplessNightsFinderTestIf2SleeplessNightsInARaw() {
        final List<String> sleepSessions = List.of("01.10.25 23:15;02.10.25 07:30;GOOD",
                "02.10.25 23:50;03.10.25 06:40;NORMAL",
                "03.10.25 14:10;03.10.25 14:50;NORMAL", //бессонная ночь с 3 на 4
                "04.10.25 06:40;04.10.25 08:00;GOOD",//бессонная ночь с 4 на 5
                "05.10.25 06:10;05.10.25 12:20;GOOD");

        List<SleepSession> sleepSessionList = new ArrayList<>();

        for (String str : sleepSessions) {
            LocalDateTime sleepSessionStart = LocalDateTime.parse(str.substring(0, str.indexOf(";")), FORMATTER);
            LocalDateTime sleepSessionEnd = LocalDateTime.parse(str.substring(str.indexOf(";") + 1, str.lastIndexOf(";")), FORMATTER);
            String sleepQuality = str.substring(str.lastIndexOf(";") + 1);

            sleepSessionList.add(new SleepSession(sleepSessionStart, sleepSessionEnd, sleepQuality));
        }

        SleeplessNightsFinder function = new SleeplessNightsFinder();
        assertEquals(2, function.apply(sleepSessionList));
    }

    //Проверка в случае 1 бессонной ночи для случая, если предыдущая сессиия заканчивается в полночь,
    //а следующая начинается в 6 утра
    @Test
    public void sleeplessNightsFinderTestIfSleepSessionEndsAtMidnightAndStartsAtSixAM() {
        final List<String> sleepSessions = List.of("01.10.25 05:15;02.10.25 07:30;GOOD",
                "02.10.25 23:50;03.10.25 06:40;NORMAL",
                "03.10.25 14:10;04.10.25 00:00;NORMAL",//бессонная ночь с 3 на 4
                "04.10.25 06:00;04.10.25 14:00;GOOD",
                "04.10.25 22:10;05.10.25 06:20;GOOD");

        List<SleepSession> sleepSessionList = new ArrayList<>();

        for (String str : sleepSessions) {
            LocalDateTime sleepSessionStart = LocalDateTime.parse(str.substring(0, str.indexOf(";")), FORMATTER);
            LocalDateTime sleepSessionEnd = LocalDateTime.parse(str.substring(str.indexOf(";") + 1, str.lastIndexOf(";")), FORMATTER);
            String sleepQuality = str.substring(str.lastIndexOf(";") + 1);

            sleepSessionList.add(new SleepSession(sleepSessionStart, sleepSessionEnd, sleepQuality));
        }

        SleeplessNightsFinder function = new SleeplessNightsFinder();
        assertEquals(1, function.apply(sleepSessionList));
    }
}
