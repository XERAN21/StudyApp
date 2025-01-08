package com.ss.studysystem.controller.startup;

import com.ss.studysystem.UI.components.modal_builder;
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

    @FXML
    void initialize(){
        configure_app.setOnAction(this::open_settings);
        close.setOnAction(this::close_stage);
        start_root.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE || event.getCode() == KeyCode.F2) {
                toggle_visibility();
            }
        });
    }

    void close_stage(ActionEvent event){
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }


    void open_settings(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ss/studysystem/Fxml/cnf_settings/config_app.fxml"));
            Parent load_view = loader.load();

            Stage stage = modal_builder.build_fixed_modal((Stage) close.getScene().getWindow(), load_view);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void toggle_visibility() {
        boolean areVisible = retry_loading.isVisible();
        retry_loading.setVisible(!areVisible);
        configure_app.setVisible(!areVisible);
    }

}

