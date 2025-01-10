package com.ss.studysystem.UI.layouts;

public enum chat_where_is_this {
    CHAT("Chatroom"),
    BOT("GEMINI"),
    FORUM("Forum"),
    CLASSROOM("Classroom"),
    ASSIGNMENT("Assignment");

    private String value;
    chat_where_is_this(String value){this.value = value;}

    public String getValue() {
        return value;
    }
}
