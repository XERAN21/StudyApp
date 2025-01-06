package com.ss.studysystem.controller.hello;

import com.ss.studysystem.Model.Gender;
import com.ss.studysystem.Model.Users;
import com.ss.studysystem.UI.logic.switch_scene;
import com.ss.studysystem.UI.model.sign_up_mdl;
import com.ss.studysystem.auth.auth_manager;
import com.ss.studysystem.database.async_service.exec_task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class sign_up_details {

    @FXML
    private DatePicker DOB;

    @FXML
    private Button confirm;

    @FXML
    private ToggleGroup gender;

    @FXML
    private Button go_back;

    sign_up_mdl sign_up_cnf = sign_up_mdl.getWeakInstance();
    Users _user = sign_up_cnf.getSign_up_user();
    exec_task exe = new exec_task();
    switch_scene switch_scene = new switch_scene();

    @FXML
    void initialize() {
        confirm.setOnAction(this::confirm_sign_up);
    }

    void confirm_sign_up(ActionEvent event) {

        if (!gender.getSelectedToggle().isSelected()) return; //todo throws error and alert
        String selected_gender = ((ToggleButton) gender.getSelectedToggle()).getText();
        _user.setGender(Gender.valueOf(selected_gender.toUpperCase()));

        if (DOB.getValue() == null) return; //todo throes error and show alert
        _user.setDob(DOB.getValue());

        //todo database
        System.out.printf("""
                        %s
                        %s
                        %s
                        %s
                        %s
                        """, _user.getEmail(), _user.getUsername()
                , _user.getPassword(), _user.getGender(), _user.getDob());

        Runnable sign_up_task = new Runnable() {
            @Override
            public void run() {
                auth_manager.add_new_user(_user);
            }
        };
        //todo move onto questionaries after database

        exe.set_on_result(result -> {
            if (result)
                switch_scene.switch_to_survey(event, (Stage) confirm.getScene().getWindow());
            else System.out.println("ERR" +
                    "" +
                    "");
        });
        exe.exec_database_task(sign_up_task, "Success", "Failure", event, (Stage) confirm.getScene().getWindow());

    }

}
