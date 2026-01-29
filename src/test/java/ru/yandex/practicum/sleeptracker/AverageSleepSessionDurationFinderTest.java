package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.analiticalfunctions.AverageSleepSessionDurationFinder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AverageSleepSessionDurationFinderTest {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    //Подсчет среднего значения при ожидаемом 363
    @Test
    public void averageSleepSessionTestIfAverageSessionEquals363() {
        final List<String> sleepSessions = List.of("01.10.25 23:15;02.10.25 07:30;GOOD",
                "02.10.25 23:50;03.10.25 06:40;NORMAL",
                "03.10.25 14:10;03.10.25 14:50;NORMAL",
                "03.10.25 23:40;04.10.25 08:00;BAD",
                "05.10.25 00:10;05.10.25 06:20;GOOD");

        List<SleepSession> sleepSessionList = new ArrayList<>();

        for (String str : sleepSessions) {
            LocalDateTime sleepSessionStart = LocalDateTime.parse(str.substring(0, str.indexOf(";")), FORMATTER);
            LocalDateTime sleepSessionEnd = LocalDateTime.parse(str.substring(str.indexOf(";") + 1, str.lastIndexOf(";")), FORMATTER);
            String sleepQuality = str.substring(str.lastIndexOf(";") + 1);

            sleepSessionList.add(new SleepSession(sleepSessionStart, sleepSessionEnd, sleepQuality));
        }

        AverageSleepSessionDurationFinder function = new AverageSleepSessionDurationFinder();
        assertEquals(363, function.apply(sleepSessionList));
    }

    //Подсчет среднего значения при ожидаемом 400
    @Test
    public void averageSleepSessionTestIfAverageSessionEquals400() {
        final List<String> sleepSessions = List.of("01.10.25 23:15;02.10.25 07:35;GOOD",
                "02.10.25 23:50;03.10.25 06:40;NORMAL",
                "03.10.25 14:10;03.10.25 16:50;NORMAL",
                "03.10.25 23:40;04.10.25 08:00;BAD",
                "05.10.25 00:10;05.10.25 07:20;GOOD");

        List<SleepSession> sleepSessionList = new ArrayList<>();

        for (String str : sleepSessions) {
            LocalDateTime sleepSessionStart = LocalDateTime.parse(str.substring(0, str.indexOf(";")), FORMATTER);
            LocalDateTime sleepSessionEnd = LocalDateTime.parse(str.substring(str.indexOf(";") + 1, str.lastIndexOf(";")), FORMATTER);
            String sleepQuality = str.substring(str.lastIndexOf(";") + 1);

            sleepSessionList.add(new SleepSession(sleepSessionStart, sleepSessionEnd, sleepQuality));
        }

        AverageSleepSessionDurationFinder function = new AverageSleepSessionDurationFinder();
        assertEquals(400, function.apply(sleepSessionList));
    }
}
