package com.ss.studysystem.UI.layouts;

public enum status_indicators {

    SUCCESS("36ff7eCA"),
    FAILURE("ff0000AA"),
    LOAD("1ea7fdA4"),
    UPDATE("FCDF78a5"),
    PROGRESS("37d5d3D1"),
    DELETE("ff5630CA");

    private final String value;

    status_indicators(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
