package com.ss.studysystem;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChangePass extends Application {

    @Override
    public void start(Stage st) throws Exception {
     
        st.setTitle("Change Password");

        // Create DropShadow effect
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.GRAY);
        shadow.setRadius(5);
        shadow.setOffsetX(2);
        shadow.setOffsetY(2);

       
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

     
        Label tlbl = new Label("Change Password");
        tlbl.setStyle("-fx-font-size: 24px; -fx-font-weight: 20px;");
        tlbl.setAlignment(Pos.CENTER);

      
        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(15);
        formGrid.setAlignment(Pos.CENTER);

     
        Label currentplbl = new Label("Current Password");
        PasswordField currentpfield = new PasswordField();
        currentpfield.setStyle("-fx-background-radius:10px; -fx-border-radius:10px; -fx-border-color: #000000; -fx-border-width: 1px");
        currentpfield.setPrefWidth(350);
        currentpfield.setEffect(shadow);

        Label newplbl = new Label("New Password");
        PasswordField newpfield = new PasswordField();
        newpfield.setStyle("-fx-background-radius:10px; -fx-border-radius:10px; -fx-border-color: #000000; -fx-border-width: 1px");
        newpfield.setPrefWidth(350);
        newpfield.setEffect(shadow);

        Label confirmplbl = new Label("Confirm New Password");
        PasswordField confirmpfield = new PasswordField();
        confirmpfield.setStyle("-fx-background-radius:10px; -fx-border-radius:10px; -fx-border-color: #000000; -fx-border-width: 1px");
        confirmpfield.setPrefWidth(350);
        confirmpfield.setEffect(shadow);

        // Adding elements to the GridPane
        formGrid.add(currentplbl, 0, 0);
        formGrid.add(currentpfield, 0, 1);
        formGrid.add(newplbl, 0, 2);
        formGrid.add(newpfield, 0, 3);
        formGrid.add(confirmplbl, 0, 4);
        formGrid.add(confirmpfield, 0, 5);

       
        HBox buttonBox = new HBox(40);
        buttonBox.setAlignment(Pos.CENTER);

        Button submitbtn = new Button("Confirm");
        Button cancelbtn = new Button("Cancel");

        submitbtn.setStyle("-fx-background-radius:10px; -fx-border-radius:10px; -fx-background-color: #8abfff; -fx-text-fill: black; -fx-border-width: 1px; -fx-border-color: #000000");
        cancelbtn.setStyle("-fx-background-radius:10px; -fx-border-radius:10px; -fx-background-color: #D18787; -fx-text-fill: black; -fx-border-width: 1px; -fx-border-color: #000000");

        submitbtn.setEffect(shadow);
        cancelbtn.setEffect(shadow);

        buttonBox.getChildren().addAll(cancelbtn, submitbtn);

     
        root.getChildren().addAll(tlbl, formGrid, buttonBox);

       
        root.setStyle(
            "-fx-background-color: #f9ebfa; " +
            "-fx-background-radius: 20; " +
            "-fx-border-radius: 20; " +
            "-fx-border-color: black; " +
            "-fx-border-width: 1;"
        );

    
        Scene scene = new Scene(root, 400, 400);
        st.setScene(scene);
        st.setResizable(false);
       
        st.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
