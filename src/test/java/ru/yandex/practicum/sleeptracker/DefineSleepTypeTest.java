package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.analiticalfunctions.DefineSleepType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefineSleepTypeTest {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    //Проверка случая, когда хронотипа "жаворонок" столько же, сколько хронотипа "сова"
    @Test
    public void defineSleepTypeTestIfLarkTypeSessionsEqualsOwlTypeSessions() {
        final List<String> sleepSessions = List.of("01.10.25 23:15;02.10.25 09:30;GOOD",
                "02.10.25 21:50;03.10.25 06:40;NORMAL",
                "03.10.25 00:10;03.10.25 10:50;NORMAL",
                "03.10.25 20:40;04.10.25 05:30;GOOD",
                "05.10.25 22:40;06.10.25 07:30;GOOD");

        List<SleepSession> sleepSessionList = new ArrayList<>();

        for (String str : sleepSessions) {
            LocalDateTime sleepSessionStart = LocalDateTime.parse(str.substring(0, str.indexOf(";")), FORMATTER);
            LocalDateTime sleepSessionEnd = LocalDateTime.parse(str.substring(str.indexOf(";") + 1, str.lastIndexOf(";")), FORMATTER);
            String sleepQuality = str.substring(str.lastIndexOf(";") + 1);

            sleepSessionList.add(new SleepSession(sleepSessionStart, sleepSessionEnd, sleepQuality));
        }

        DefineSleepType function = new DefineSleepType();
        assertEquals(SleepTypes.PIGEON, function.apply(sleepSessionList));
    }

    //Проверка случая, когда хронотипа "жаворонок" столько же, сколько хронотипа "голубь"
    @Test
    public void defineSleepTypeTestIfLarkTypeSessionsEqualsPigeonTypeSessions() {
        final List<String> sleepSessions = List.of("01.10.25 22:30;02.10.25 08:30;GOOD",
                "02.10.25 21:50;03.10.25 06:40;NORMAL",
                "03.10.25 23:10;04.10.25 08:50;NORMAL",
                "04.10.25 20:40;05.10.25 05:30;GOOD",
                "05.10.25 23:40;06.10.25 09:30;GOOD");

        List<SleepSession> sleepSessionList = new ArrayList<>();

        for (String str : sleepSessions) {
            LocalDateTime sleepSessionStart = LocalDateTime.parse(str.substring(0, str.indexOf(";")), FORMATTER);
            LocalDateTime sleepSessionEnd = LocalDateTime.parse(str.substring(str.indexOf(";") + 1, str.lastIndexOf(";")), FORMATTER);
            String sleepQuality = str.substring(str.lastIndexOf(";") + 1);

            sleepSessionList.add(new SleepSession(sleepSessionStart, sleepSessionEnd, sleepQuality));
        }

        DefineSleepType function = new DefineSleepType();
        assertEquals(SleepTypes.PIGEON, function.apply(sleepSessionList));
    }

    //Проверка случая, когда хронотипа "жаворонок" больше всех
    @Test
    public void defineSleepTypeTestIfLarkTypeSessionsIsLargest() {
        final List<String> sleepSessions = List.of("01.10.25 21:30;02.10.25 06:30;GOOD",
                "02.10.25 21:50;03.10.25 06:40;NORMAL",
                "03.10.25 23:10;04.10.25 08:50;NORMAL",
                "04.10.25 20:40;05.10.25 05:30;GOOD",
                "05.10.25 23:40;06.10.25 09:30;GOOD");

        List<SleepSession> sleepSessionList = new ArrayList<>();

        for (String str : sleepSessions) {
            LocalDateTime sleepSessionStart = LocalDateTime.parse(str.substring(0, str.indexOf(";")), FORMATTER);
            LocalDateTime sleepSessionEnd = LocalDateTime.parse(str.substring(str.indexOf(";") + 1, str.lastIndexOf(";")), FORMATTER);
            String sleepQuality = str.substring(str.lastIndexOf(";") + 1);

            sleepSessionList.add(new SleepSession(sleepSessionStart, sleepSessionEnd, sleepQuality));
        }

        DefineSleepType function = new DefineSleepType();
        assertEquals(SleepTypes.LARK, function.apply(sleepSessionList));
    }

    //Проверка случая, когда хронотипа "голубь" больше всех
    @Test
    public void defineSleepTypeTestIfPigeonTypeSessionsIsLargest() {
        final List<String> sleepSessions = List.of("01.10.25 23:15;02.10.25 07:30;GOOD",
                "02.10.25 21:50;03.10.25 06:40;NORMAL",
                "03.10.25 14:10;03.10.25 14:50;NORMAL",
                "03.10.25 23:40;04.10.25 08:00;GOOD",
                "05.10.25 00:10;05.10.25 06:20;GOOD",
                "05.10.25 23:40;06.10.25 09:30;GOOD");

        List<SleepSession> sleepSessionList = new ArrayList<>();

        for (String str : sleepSessions) {
            LocalDateTime sleepSessionStart = LocalDateTime.parse(str.substring(0, str.indexOf(";")), FORMATTER);
            LocalDateTime sleepSessionEnd = LocalDateTime.parse(str.substring(str.indexOf(";") + 1, str.lastIndexOf(";")), FORMATTER);
            String sleepQuality = str.substring(str.lastIndexOf(";") + 1);

            sleepSessionList.add(new SleepSession(sleepSessionStart, sleepSessionEnd, sleepQuality));
        }

        DefineSleepType function = new DefineSleepType();
        assertEquals(SleepTypes.PIGEON, function.apply(sleepSessionList));
    }

    //Проверка случая, когда хронотипа "сова" больше всех
    @Test
    public void defineSleepTypeTestIfOwlTypeSessionsIsLargest() {
        final List<String> sleepSessions = List.of("02.10.25 03:30;02.10.25 09:30;GOOD",
                "02.10.25 22:50;03.10.25 07:40;NORMAL",
                "03.10.25 23:10;04.10.25 09:50;NORMAL",
                "04.10.25 20:40;05.10.25 05:30;GOOD");

        List<SleepSession> sleepSessionList = new ArrayList<>();

        for (String str : sleepSessions) {
            LocalDateTime sleepSessionStart = LocalDateTime.parse(str.substring(0, str.indexOf(";")), FORMATTER);
            LocalDateTime sleepSessionEnd = LocalDateTime.parse(str.substring(str.indexOf(";") + 1, str.lastIndexOf(";")), FORMATTER);
            String sleepQuality = str.substring(str.lastIndexOf(";") + 1);

            sleepSessionList.add(new SleepSession(sleepSessionStart, sleepSessionEnd, sleepQuality));
        }

        DefineSleepType function = new DefineSleepType();
        assertEquals(SleepTypes.OWL, function.apply(sleepSessionList));
    }
}
