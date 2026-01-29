package ru.yandex.practicum.sleeptracker;

public enum SleepTypes {
    OWL("Сова"),
    LARK("Жаворонок"),
    PIGEON("Голубь");

    private final String type;

    SleepTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
