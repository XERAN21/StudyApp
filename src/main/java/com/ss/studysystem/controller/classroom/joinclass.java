package com.ss.studysystem.controller.classroom;

import com.ss.studysystem.Model.Classrooms;
import com.ss.studysystem.UI.components.modal_builder;
import com.ss.studysystem.UI.layouts.chat_where_is_this;
import com.ss.studysystem.controller.create_and_join;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class joinclass {

    @FXML
    private Button btnjoin;

    @FXML
    void initialize(){
        btnjoin.setOnAction(this::joinclass);
    }

    Consumer<Classrooms> on_class;

    public void setOn_class(Consumer<Classrooms> on_class) {
        this.on_class = on_class;
    }

    @FXML
    void joinclass(ActionEvent event) {
       try {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ss/studysystem/Fxml/Create and Join.fxml"));
           Parent load_view = loader.load();

           create_and_join createAndJoin = loader.getController();
           createAndJoin.setLocation(chat_where_is_this.CLASSROOM);
           createAndJoin.change_placeholder("Create Classroom");
           createAndJoin.setOn_classroom(on_class);

           Stage stage = modal_builder.build_fixed_modal((Stage) btnjoin.getScene().getWindow(), load_view, 385,305);

           stage.show();

       }catch (Exception e){
           e.printStackTrace();

       }
    }

}
