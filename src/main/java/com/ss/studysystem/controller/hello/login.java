package com.ss.studysystem.controller.hello;

import com.ss.studysystem.Model.Users;
import com.ss.studysystem.UI.components.modal_builder;
import com.ss.studysystem.UI.logic.switch_scene;
import com.ss.studysystem.auth.auth_manager;
import com.ss.studysystem.controller.login_error_message;
import com.ss.studysystem.database.controller.user_controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.regex.Pattern;

public class login {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    @FXML
    private Button confirm;

    @FXML
    private Button create_new_account;

    @FXML
    private Button forgot_password;

    @FXML
    private PasswordField user_password;

    @FXML
    private TextField user_textfield;

    switch_scene switcher = new switch_scene();

    @FXML
    void initialize() {
        create_new_account.setOnAction(this::sign_up);
        confirm.setOnAction(this::confirmation);
        forgot_password.setOnAction(this::fn_forgot_password);
    }

    @FXML
    void sign_up(ActionEvent event) {
        try {
            switcher.switchToSignUpScene(event, (Stage) confirm.getScene().getWindow());
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    @FXML
    void confirmation(ActionEvent event) {

        String user_input = user_textfield.getText();
        String password = user_password.getText();
        Users curr_user;


        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ss/studysystem/Fxml/quiz/login_error_message.fxml"));
            Parent load_view = loader.load();
            login_error_message loginErrorMessage = loader.getController();
            Stage stage = modal_builder.build_fixed_modal((Stage) confirm.getScene().getWindow(), load_view, 385,305);

            if (user_input == null || user_input.trim().isEmpty() && password == null || password.trim().isEmpty()){
                //todo popup error msg;
                loginErrorMessage.setErrorMessage("Error occurred in the login process you need to fill in these blank");
                stage.show();
                return;
            } else if (user_input == null || user_input.trim().isEmpty()) {
                //todo popupp error msg;
                loginErrorMessage.setErrorMessage("Please fill username (or) email!");
                stage.show();
                return;
            }else if(password == null || password.trim().isEmpty()){
                loginErrorMessage.setErrorMessage("Please fill in password field!");
                stage.show();
                return;
            }else{
                //todo popupp error msg;
                loginErrorMessage.setErrorMessage("An error in login form!");
                stage.show();
            }

            try{
                if (isEmail(user_input)){
                   curr_user =  user_controller.get_user_by_email(user_input);
                }else {
                    curr_user = user_controller.get_user_by_username(user_input);
                }

                if(curr_user == null) return; //todo throws error & notification

                //todo password validate
                boolean check = auth_manager.verify_password(password,curr_user.getPassword(),curr_user.getSalt());

                if (check){
                    //todo if true -> login(switch scenes)
                }else {
                    //todo if false -> throws error & notifi for incorrect password
                }


            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    //checking if user_input got email or username
    public static boolean isEmail(String eamil){
        return EMAIL_PATTERN.matcher(eamil).matches();
    }

    @FXML
    void fn_forgot_password(ActionEvent event){

    }

}
