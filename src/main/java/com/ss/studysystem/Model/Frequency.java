package com.ss.studysystem.Model;

public enum Frequency {
    MON("Monday"),
    TUE("Tuesday"),
    WED("Wednesday"),
    THU("Thursday"),
    FRI("Friday"),
    SAT("Saturday"),
    SUN("Sunday"),
    DAILY("Daily");

    private final String value;

    Frequency(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
