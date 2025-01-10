package com.ss.studysystem.controller;

import com.ss.studysystem.Model.Classrooms;
import com.ss.studysystem.UI.layouts.chat_where_is_this;
import com.ss.studysystem.UI.misc.modal_animations;
import javafx.animation.ParallelTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.reactfx.util.LL;

import java.util.function.Consumer;

public class create_and_join {

    @FXML
    private Label placeholder;

    @FXML
    private Button create;

    @FXML
    private Button join;

    @FXML
    private Button close;

    private static chat_where_is_this location;

    private Consumer<Classrooms> on_classroom;

    public Consumer<Classrooms> getOn_classroom() {
        return on_classroom;
    }

    public void setOn_classroom(Consumer<Classrooms> on_classroom) {
        this.on_classroom = on_classroom;
    }

    public void setLocation(chat_where_is_this location) {
        this.location = location;
        Platform.runLater(()->placeholder.setText("Create " +location.getValue()));
    }

    public static chat_where_is_this getlocation(){
        return location;
    }

    @FXML
    void initialize(){
        create.setOnAction(this::Create);
        join.setOnAction(this::Join);
        close.setOnAction(this::setClose);
    }

    @FXML
    void Create(ActionEvent event){

        try{

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ss/studysystem/Fxml/CreateGroup2.fxml"));
            Parent load_view = loader.load();

            Create_locationCG create_group_cnf = loader.getController();
            create_group_cnf.setLocation(location);
            if(location.equals(chat_where_is_this.CLASSROOM)){
                create_group_cnf.setOn_result(on_classroom);
            }

            Stage stage = (Stage) create.getScene().getWindow();
            Scene sc = new Scene(load_view,stage.getWidth(),stage.getHeight(), Color.TRANSPARENT);
            stage.setScene(sc);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //todo Complete Join function
    @FXML
    void Join(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ss/studysystem/Fxml/modals/invi_code_join.fxml"));
            Parent load_view = loader.load();

            Stage stage = (Stage) join.getScene().getWindow();
            Scene sc = new Scene(load_view, stage.getWidth(), stage.getHeight(), Color.TRANSPARENT);
            stage.setScene(sc);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void change_placeholder(String text){
        Platform.runLater(()->{
            placeholder.setText(text);

        });
    }

    @FXML
    void setClose(ActionEvent event){
        Stage stage = (Stage) close.getScene().getWindow();
        Parent view = stage.getScene().getRoot();
        ParallelTransition closeAnimation =  modal_animations.close_modal_w_size(view, stage.getWidth(), stage.getHeight());
        closeAnimation.setOnFinished(e -> stage.close());

        closeAnimation.play();
//        stage.close();
    }

    public String get_placeholder(){
         String text = placeholder.getText();
         return text;
    }
}
