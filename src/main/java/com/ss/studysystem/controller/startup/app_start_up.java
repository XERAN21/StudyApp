package com.ss.studysystem.controller.startup;

import com.ss.studysystem.UI.components.modal_builder;
import com.ss.studysystem.UI.logic.switch_scene;
import com.ss.studysystem.cnf.user_cnf;
import com.ss.studysystem.database.async_service.exec_task;
import com.ss.studysystem.database.controller.user_controller;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class app_start_up {

    @FXML
    private Button close;

    @FXML
    private Button configure_app;

    @FXML
    private Text msg;

    @FXML
    private Button retry_loading;

    @FXML
    private GridPane start_root;

    user_cnf user_pref = user_cnf.get_instance();
    exec_task exe = new exec_task();
    switch_scene switcher = new switch_scene();

    @FXML
    void initialize() {

        user_pref.load();

        PauseTransition pause = new javafx.animation.PauseTransition(javafx.util.Duration.seconds(3));
        pause.setOnFinished(e -> {
            Platform.runLater(() -> retry_loading.fire());
        });

        configure_app.setOnAction(this::open_settings);
        close.setOnAction(this::close_stage);
        start_root.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE || event.getCode() == KeyCode.F2) {
                toggle_visibility();
                pause.stop();
            }
        });

        retry_loading.setOnAction(this::retry);

        pause.play();

    }

    void retry(ActionEvent event) {

        retry_loading.setVisible(false);
        configure_app.setVisible(false);

        exe.set_on_result(result -> {
            if (result) {
                switcher.switch_to_survey(event, (Stage) close.getScene().getWindow());
            } else {
                System.out.println("FAiled");
                switcher.switchToLoginScene(event, (Stage) close.getScene().getWindow());
            }
        });


        exe.exec_database_task_no_loader(()->user_controller.login(user_pref.getUser().getEmail(),
                user_pref.getUser().getPassword()),
                "LOGIN", "FAILED",
                event, (Stage) close.getScene().getWindow());


    }

    void close_stage(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }


    void open_settings(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ss/studysystem/Fxml/cnf_settings/config_app.fxml"));
            Parent load_view = loader.load();

            Stage stage = modal_builder.build_fixed_modal((Stage) close.getScene().getWindow(), load_view);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void toggle_visibility() {
        boolean areVisible = retry_loading.isVisible();
        retry_loading.setVisible(!areVisible);
        configure_app.setVisible(!areVisible);
    }

}

