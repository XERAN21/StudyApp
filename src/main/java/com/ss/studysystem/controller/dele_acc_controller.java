package com.ss.studysystem.controller;

import com.ss.studysystem.Model.Users;
import com.ss.studysystem.auth.auth_manager;
import com.ss.studysystem.database.controller.user_controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class dele_acc_controller {

    @FXML
    private TextField pass_text;

    @FXML
    private Button btn_con;

    @FXML
    private Button btn_cancel;

    private Consumer<Boolean> onResult;


    @FXML
    void initialize(){
        btn_cancel.setOnAction(this::cancel);
        btn_con.setOnAction(this::confirmation);
    }

    @FXML
    void cancel(ActionEvent event) {
        if (onResult != null) {
            onResult.accept(false);
        }

        Button clicked = (Button) event.getSource();
        Stage stage = (Stage) clicked.getScene().getWindow();
        stage.close();
    }

    @FXML
    void confirmation(ActionEvent event) {
        String password = pass_text.getText();

        //todo change uid
        Users curr_user = user_controller.get_user_by_id(1);

        if (password == null || password.trim().isEmpty()){
            
        }else {
            
        }
        
        try {
            boolean check = auth_manager.verify_password(password,curr_user.getPassword(),curr_user.getSalt());

        if (check){
            user_controller.delete_user(curr_user);
        }else {

        }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
