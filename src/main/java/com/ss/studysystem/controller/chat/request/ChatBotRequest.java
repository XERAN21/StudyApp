package com.ss.studysystem.controller.chat.request;

public class ChatBotRequest {
    private String action;
    private String message;
    private boolean bot;

    public ChatBotRequest(String action, String message, boolean bot) {
        this.action = action;
        this.message = message;
        this.bot = bot;
    }

}