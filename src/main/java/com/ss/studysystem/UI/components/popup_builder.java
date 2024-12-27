package com.ss.studysystem.UI.components;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class popup_builder {
    public static Popup show_notification(Button owner, Node view){
        Popup popup = new Popup();
        popup.setAutoHide(true);

        popup.getContent().add(view);
        return popup;
    }
}
