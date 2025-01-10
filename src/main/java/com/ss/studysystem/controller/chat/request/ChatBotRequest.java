package com.ss.studysystem.controller.chat.request;

public class ChatBotRequest {
    private String uid;
    private String action;
    private String message;
    private boolean bot;

    public ChatBotRequest(String action, String uid, String message, boolean bot) {
        this.action = action;
        this.uid = uid;
        this.message = message;
        this.bot = bot;
    }

}