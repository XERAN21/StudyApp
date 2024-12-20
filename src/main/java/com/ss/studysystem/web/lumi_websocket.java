package com.ss.studysystem.web;

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

        String serverUri = "ws://localhost:6789";
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
}
