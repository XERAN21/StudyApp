package com.ss.studysystem.controller.hello;

import com.ss.studysystem.Model.Users;
import com.ss.studysystem.UI.components.modal_builder;
import com.ss.studysystem.UI.logic.switch_scene;
import com.ss.studysystem.cnf.user_cnf;
import com.ss.studysystem.controller.error_handler.login_error_message;
import com.ss.studysystem.database.async_service.exec_task;
import com.ss.studysystem.database.controller.user_controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.concurrent.Callable;
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
    exec_task exe = new exec_task();
    user_cnf user_pref = user_cnf.get_instance();

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

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ss/studysystem/Fxml/Error_msg/login_error_message.fxml"));
            Parent load_view = loader.load();
            login_error_message loginErrorMessage = loader.getController();
            Stage stage = modal_builder.build_fixed_modal((Stage) confirm.getScene().getWindow(), load_view, 385, 305);

            if (user_input == null || user_input.trim().isEmpty() && password == null || password.trim().isEmpty()) {
                //todo popup error msg;
                loginErrorMessage.set_ErrorMessage("Error occurred in the login process you need to fill in these blank");
                stage.show();
                return;
            } else if (user_input == null || user_input.trim().isEmpty()) {
                //todo popupp error msg;
                loginErrorMessage.set_ErrorMessage("Please fill username (or) email!");
                stage.show();
                return;
            } else if (password == null || password.trim().isEmpty()) {
                loginErrorMessage.set_ErrorMessage("Please fill in password field!");
                stage.show();
                return;
            }

            Users[] user = new Users[1];
            try {
                Callable<Users> call_login = new Callable<Users>() {
                    @Override
                    public Users call() throws Exception {
                        user[0] = user_controller.login(user_input, password);
                        return user[0];
                    }
                };

                exe.set_on_result(result -> {
                    if (result) {
                        if (user[0] == null) {
                            System.out.println("FAIL");
                        } else {
                            System.out.println("SUCCESS");
                            user_pref.save(user[0]);
                            switcher.switch_to_survey(event, (Stage) confirm.getScene().getWindow());
                        }
                    } else {
                        System.out.println("FAILED");
                    }
                });

                exe.exec_database_task(call_login, "Success Message", "Failure Message", event, (Stage) confirm.getScene().getWindow());


            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    //checking if user_input got email or username
    public static boolean isEmail(String eamil) {
        return EMAIL_PATTERN.matcher(eamil).matches();
    }

    @FXML
    void fn_forgot_password(ActionEvent event) {

    }

}
