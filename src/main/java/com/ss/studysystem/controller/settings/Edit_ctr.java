package com.ss.studysystem.controller.settings;

//import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import com.ss.studysystem.UI.components.modal_builder;
import com.ss.studysystem.UI.misc.profile_pattern;
import com.ss.studysystem.controller.image_editor.image_editor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Edit_ctr {

    @FXML
    private Label email;

    @FXML
    private Circle profile_img;

    @FXML
    private Circle edit_img;

    @FXML
    private Button switch_edit;

    @FXML
    private Label username;

    @FXML
    void initialize() {
        switch_edit.setOnAction(this::edit);

        profile_img.setOnMouseEntered(event -> {
            edit_img.setVisible(true);
        });

        profile_img.setOnMouseExited(event -> {
            edit_img.setVisible(false);
        });

        //todo content view
        profile_img.setOnMouseClicked(event -> open_image_editor());
    }

    @FXML
    void edit(ActionEvent event) {
        try {
            URL path3 = getClass().getResource("/com/ss/studysystem/Fxml/Profile/Edit User.fxml");
            FXMLLoader loader = new FXMLLoader(path3);
            Parent dele = loader.load();
            image_editor img_ctrl = loader.getController();
            Stage modalStage = modal_builder.build_fixed_modal((Stage) switch_edit.getScene().getWindow(), dele);
            modalStage.show();

            img_ctrl.setOn_img(result->{
                try {
                    System.out.println("done!");
                    File f = profile_pattern.imageToTempFile(result);
                    ImagePattern img_ptrn = profile_pattern.generate_profile_image(f);
                    profile_img.setFill(img_ptrn);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void open_image_editor() {
        try {
            URL path3 = getClass().getResource("/com/ss/studysystem/Fxml/profile_img/image_selector.fxml");
            Parent dele = FXMLLoader.load(path3);

            Stage modalStage = modal_builder.build_fixed_modal((Stage) switch_edit.getScene().getWindow(), dele);
            modalStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        public void setUserDetails(String username, String email) {
//            this.username.setText(username); this.email.setText(email); }
//    }
    }
}


