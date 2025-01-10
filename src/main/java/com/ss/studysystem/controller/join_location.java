package com.ss.studysystem.controller;

import com.ss.studysystem.Model.*;
import com.ss.studysystem.UI.layouts.chat_where_is_this;
import com.ss.studysystem.cnf.user_cnf;
import com.ss.studysystem.web.lumi_websocket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import com.ss.studysystem.database.controller.*;

public class join_location {

    @FXML
    private TextField input_field = new TextField();

    @FXML
    private Button cancle_btn;

    @FXML
    private Button confirm_btn;

    String invi_code = input_field.getText();
    chat_where_is_this location = create_and_join.getlocation();
    user_cnf user = user_cnf.get_instance();

    @FXML
    void initialize(){
        System.out.println("Input Field: " + input_field);
        cancle_btn.setOnAction(this::close);
        confirm_btn.setOnAction(this::confirm);
        user.load();
    }

    @FXML
    void close(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ss/studysystem/Fxml/Create and Join.fxml"));
            Parent load_view = loader.load();

            create_and_join createAndJoin = loader.getController();
            switch (location){
                case CHAT:
                    createAndJoin.setLocation(chat_where_is_this.CHAT);
                    break;
                case CLASSROOM:
                    createAndJoin.setLocation(chat_where_is_this.CLASSROOM);
                    break;
            }

            Stage stage = (Stage) cancle_btn.getScene().getWindow();
            Scene sc = new Scene(load_view, stage.getWidth(), stage.getHeight(), Color.TRANSPARENT);
            stage.setScene(sc);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //todo need to check
    @FXML
    void confirm(ActionEvent event) {
        switch (location){
            case CHAT:
                join_chatroom();
                System.out.println("work");
                break;
            case CLASSROOM:
                join_classroom();
                System.out.println("work");
                break;
        }
    }

    public void join_chatroom(){
//        Chatroom_invite chat_invite = new Chatroom_invite();
//
//        Chatrooms chatrooms = new Chatrooms();
//        chatrooms.setChartroom_id(chat_invite.getChatrooms().getChartroom_id());
//
//        Users users = new Users();
//        users.setId(1);
//
//        User_Chatroom user_chatroom = new User_Chatroom();
//        user_chatroom.setUser(users);
//        user_chatroom.setChatroom(chatrooms);
//
//        if (invi_code.equals(chat_invite.getInvite_code())){
//            return Chatroom_controller.addUserToChatroom(user_chatroom);
//        }else {
//            return false;
//        }
        String code = input_field.getText();
        lumi_websocket.getInstance().join_room(code);

    }

    public boolean join_classroom(){
        Classroom_invite class_invite = new Classroom_invite();

        Classrooms classrooms = new Classrooms();
        classrooms.setId(class_invite.getClassrooms().getId());

        Users users = new Users();
        users.setId(user.getUser().getId()); //todo how to get user_id?

        User_Classroom user_classroom = new User_Classroom();
        user_classroom.setClassrooms(classrooms);
        user_classroom.setUser(users);

        if (invi_code.equals(class_invite.getInvite_code())){
            return classroom_controller.add_member(user_classroom);

        }else {
            return false;
        }
    }

}
