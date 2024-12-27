package com.ss.studysystem.UI.components;

import javafx.scene.Node;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class popup_builder {
    public static Popup show_notification(Stage owner, Node view){
        Popup popup = new Popup();
        popup.setAutoHide(true);

        popup.getContent().add(view);
        popup.show(owner);
        return popup;
    }
}
