package com.ss.studysystem.UI.components;

import javafx.stage.Popup;
import javafx.stage.Stage;

public class popup_builder {
    public static void show_notification(Stage owner){
        Popup popup = new Popup();



        popup.show(owner);
    }
}
