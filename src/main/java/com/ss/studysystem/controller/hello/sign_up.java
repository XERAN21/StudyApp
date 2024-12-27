package com.ss.studysystem.controller.hello;

import com.ss.studysystem.Model.Users;
import com.ss.studysystem.UI.model.sign_up_mdl;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.util.List;

/**
 * @REFERENCES UI/model/sign_up
 */
public class sign_up {

    @FXML
    private TextField email;

    @FXML
    private Button login_acc;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField password_confirm;

    @FXML
    private Button next; //REF -> ui/model/signup

    @FXML
    private TextField username;

    sign_up_mdl sign_up_cnf = sign_up_mdl.getWeakInstance();
    Users _user = sign_up_cnf.getSign_up_user();

    @FXML
    void initialize(){

        //todo email, password, next, username

        next.setOnAction(this::switchToDetails);
    }

    private void switchToDetails(ActionEvent event) {

        //todo all fields shouldn't be empty

        _user.setUsername(username.getText().trim());
        _user.setEmail(email.getText().trim());

        //todo password == password_password
        if(!password.getText().matches(password_confirm.getText())) return; //todo throws error & show alert box
        _user.setPassword(password.getText());

        //todo email exists? username exists?

        switchNode(sign_up_cnf.getDetailsNode());
    }

    private void switchNode(Node targetNode) {
        Platform.runLater(() -> {
            HBox root = sign_up_cnf.getRoot();
            root.getChildren().remove(sign_up_cnf.getCurrentNode());
            sign_up_cnf.setCurrentNode(targetNode);
            root.getChildren().add(targetNode);
        });
    }
}
