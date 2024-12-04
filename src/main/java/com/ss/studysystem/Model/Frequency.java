package com.ss.studysystem.Model;

public enum Frequency {
    MON("MONDAY"),
    TUE("Tuesday"),
    WED("Wednesday"),
    THU("Thursday"),
    FRI("Friday"),
    SAT("Saturday"),
    SUN("Sunday"),
    DAILY("Daily");

    private String value;

   Frequency(String value){
       this.value = value;
   }

    public String getValue() {
        return value;
    }
}
