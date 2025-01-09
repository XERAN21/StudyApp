package com.ss.studysystem.web;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ss.studysystem.controller.chat.request.ChatBotRequest;
import com.ss.studysystem.web.request.request_type;
import javafx.application.Platform;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class lumi_websocket {

    private WebSocketClient webSocketClient;
    private static lumi_websocket instance;
    private message_handler messageHandler;

    private lumi_websocket() {
        connect();
    }

    public static lumi_websocket getInstance() {
        if (instance == null) {
            instance = new lumi_websocket();
        }
        return instance;
    }

    public void setMessageHandler(message_handler handler) {
        this.messageHandler = handler;
    }

    public void clearMessageHandler() {
        this.messageHandler = null;
    }

    private void connect() {
        if (webSocketClient != null && webSocketClient.isOpen()) {
            return;
        }

        String ip = "0.0.0.0";
        String serverUri = String.format("ws://%s:6789", ip);
        connectWebSocket(serverUri);
    }

    private void connectWebSocket(String uri) {
        try {
            webSocketClient = new WebSocketClient(new URI(uri)) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    System.out.println("Connected to WebSocket server");
                }

                @Override
                public void onMessage(String message) {
                    Platform.runLater(() -> {
                        if (messageHandler != null) {
                            messageHandler.handleMessage(message);
                        }
                    });
                    System.out.println("Received: " + message);
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    System.out.println("WebSocket closed: " + reason);
                }

                @Override
                public void onError(Exception ex) {
                    System.out.println("WebSocket error: " + ex.getMessage());
                }

            };

            webSocketClient.connect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (webSocketClient != null && webSocketClient.isOpen()) {
            webSocketClient.close();
            System.out.println("WebSocket connection closed");
        }
    }

    public void sendMsg(String msg) {
        if (webSocketClient != null && webSocketClient.isOpen()) {
            webSocketClient.send(msg);
            System.out.println("Sent: " + msg);
        }
    }

    public void use_bot(String roomCode) {
        System.out.println("r");
        if (webSocketClient != null && webSocketClient.isOpen()) {
            JsonObject joinMessage = new JsonObject();
            joinMessage.addProperty("action", "join_room");
            joinMessage.addProperty("room_code", roomCode);

            webSocketClient.send(joinMessage.toString());
            System.out.println("Sent: " + joinMessage.toString());
        }
    }

    public void create_room(String roomCode) {
        System.out.println("c");
        if (webSocketClient != null && webSocketClient.isOpen()) {
            use_bot(roomCode);
            Gson gson = new Gson();
            request_type request = new request_type("create_room", roomCode);
            String jsonMessage = gson.toJson(request);
//            JsonObject create_msg = new JsonObject();
//            create_msg.addProperty("action", "create_room");
//            create_msg.addProperty("room_name", roomCode);

            System.out.println("Sent: " + request);
            webSocketClient.send(jsonMessage);


        }
    }

}
