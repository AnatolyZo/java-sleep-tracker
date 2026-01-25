package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.analiticalfunctions.*;
import ru.yandex.practicum.sleeptracker.coverfunctions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SleepTrackerApp {
    static final String SLEEP_LOG_DIRECTORY = "src/main/resources/sleep_log.txt";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    public static void main(String[] args) {
        List<SleepSession> sleepData = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(SLEEP_LOG_DIRECTORY, StandardCharsets.UTF_8))) {
            sleepData = br.lines()
                    .map(str -> {
                        LocalDateTime sleepSessionStart = LocalDateTime.parse(str.substring(0, str.indexOf(";")), FORMATTER);
                        LocalDateTime sleepSessionEnd = LocalDateTime.parse(str.substring(str.indexOf(";") + 1, str.lastIndexOf(";")), FORMATTER);
                        String sleepQuality = str.substring(str.lastIndexOf(";") + 1);
                        return new SleepSession(sleepSessionStart, sleepSessionEnd, sleepQuality);
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        List<Function<List<SleepSession>, String>> functionsList = new ArrayList<>();
        try {
            if (sleepData.isEmpty()) {
                throw new SleepLogIsEmptyException("В файле отсутствуют записи сессий сна, анализ невозможен.");
            }

            functionsList.add(new SleepSessionsCounterCover());
            functionsList.add(new MinSleepSessionFinderCover());
            functionsList.add(new MaxSleepSessionFinderCover());
            functionsList.add(new AverageSleepSessionDurationFinderCover());
            functionsList.add(new BadQualitySessionFinderCover());
            functionsList.add(new SleeplessNightsFinderCover());
            functionsList.add(new DefineSleepTypeCover());
        } catch (SleepLogIsEmptyException e) {
            System.out.println(e.getMessage());
        }


        final List<SleepSession> sleepDataStream = sleepData;

        functionsList.stream().map(function -> function.apply(sleepDataStream)).forEach(System.out::println);
    }
}