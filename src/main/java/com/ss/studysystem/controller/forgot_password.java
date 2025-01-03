package com.ss.studysystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.sourceforge.plantuml.nwdiag.next.NStage;

public class forgot_password {

    @FXML
    private TextField email_input;

    @FXML
    private Button confirm;

    @FXML
    private Button close;

    @FXML
    void initialize(){
        confirm.setOnAction(this::confirm);
        close.setOnAction(this::closePWField);
    }

    @FXML
    void checkinput(ActionEvent event){
        String email = email_input.getText();
        if (isValidEmail(email)) {
            if (isEmailInDatabase(email)) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Email is valid and exists in the database.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Email Not Found", "The email address is not registered in our database.");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Invalid Email", "Please enter a valid email address.");
        }
    }

    @FXML
    void confirm(ActionEvent event){
        //todo check the input is email format
        //todo check there's email in database if true sent(switch scene)
        checkinput(event);
    }

    @FXML
    void closePWField(ActionEvent event){
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    private boolean isEmailInDatabase(String email) {
        return true;
      }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

    }
}
