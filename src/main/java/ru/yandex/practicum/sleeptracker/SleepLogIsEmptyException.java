package ru.yandex.practicum.sleeptracker;

public class SleepLogIsEmptyException extends Exception{
    public SleepLogIsEmptyException(String message) {
        super(message);
    }
}
