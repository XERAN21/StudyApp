package com.ss.studysystem.database.controller;

import com.ss.studysystem.Model.Chatroom_invite;
import com.ss.studysystem.Model.Chatrooms;
import com.ss.studysystem.Model.Classroom_invite;
import com.ss.studysystem.Model.Classrooms;
import com.ss.studysystem.database.connection.DB_Connection;

import java.sql.*;
import java.time.LocalDateTime;

//todo Testing and get all invitation
public class invitation_controller {

    private static boolean create_classroom_invitation(Classroom_invite classroom_invite){
        String sql = "Call create_classroom_invitation(?,?,?,?,?)";
        try(Connection connection = DB_Connection.Get_Connection();
            CallableStatement callableStatement = connection.prepareCall(sql)){

            callableStatement.setInt(1, classroom_invite.getClassrooms().getId());
            callableStatement.setString(2,classroom_invite.getInvite_code());
            callableStatement.setTimestamp(3, Timestamp.valueOf(classroom_invite.getCreated_at()));
            callableStatement.setTimestamp(4,Timestamp.valueOf(classroom_invite.getExpire_at()));
            callableStatement.setBoolean(5,classroom_invite.isIs_used());

            int row_affected = callableStatement.executeUpdate();

            return row_affected > 0;
        }catch (SQLException e){
            return false;
        }
    }

    private static boolean create_chatroom_invitation(Chatroom_invite chatroom_invite){
        String sql= "Call create_chatroom_invitation(?,?,?,?,?)";
        try(Connection connection = DB_Connection.Get_Connection();
            CallableStatement callableStatement = connection.prepareCall(sql)){

            callableStatement.setInt(1,chatroom_invite.getChatrooms().getChartroom_id());
            callableStatement.setString(2,chatroom_invite.getInvite_code());
            callableStatement.setTimestamp(3,Timestamp.valueOf(chatroom_invite.getCreated_at()));
            callableStatement.setTimestamp(4,Timestamp.valueOf(chatroom_invite.getExpire_at()));
            callableStatement.setBoolean(5,chatroom_invite.isIs_used());

            int row_affected = callableStatement.executeUpdate();
            return row_affected > 0;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    private static Classroom_invite get_classroom_invitation(int invitation_id){
        String sql= "Call get_classroom_invitation(?)";
        Classroom_invite classroom_invite = new Classroom_invite();

        try(Connection connection = DB_Connection.Get_Connection();
            CallableStatement callableStatement = connection.prepareCall(sql)){

            callableStatement.setInt(1,invitation_id);

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()){
                Classrooms classrooms = new Classrooms();
                classrooms.setId(resultSet.getInt("classroom_id"));

                classroom_invite.setClassrooms(classrooms);
                classroom_invite.setInvite_code(resultSet.getString("invite_code"));
                classroom_invite.setCreated_at(resultSet.getTimestamp("created_at").toLocalDateTime());
                classroom_invite.setExpire_at(resultSet.getTimestamp("expire_at").toLocalDateTime());
                classroom_invite.setIs_used(resultSet.getBoolean("is_used"));

            }

            return classroom_invite;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    private static Chatroom_invite get_chatroom_invitation(int invitation_id){
        String sql= "Call get_classroom_invitation(?)";
        Chatroom_invite chatroom_invite = new Chatroom_invite();

        try(Connection connection = DB_Connection.Get_Connection();
            CallableStatement callableStatement = connection.prepareCall(sql)){

            callableStatement.setInt(1,invitation_id);

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()){
                Chatrooms chatrooms = new Chatrooms();
                chatrooms.setChartroom_id(resultSet.getInt("classroom_id"));

                chatroom_invite.setChatrooms(chatrooms);
                chatroom_invite.setInvite_code(resultSet.getString("invite_code"));
                chatroom_invite.setCreated_at(resultSet.getTimestamp("created_at").toLocalDateTime());
                chatroom_invite.setExpire_at(resultSet.getTimestamp("expire_at").toLocalDateTime());
                chatroom_invite.setIs_used(resultSet.getBoolean("is_used"));

            }
            return chatroom_invite;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    //todo testing

//    public class Main {
//        public static void main(String[] args) {
//            // Create a new classroom invitation
//            Classrooms classroom = new Classrooms();
//            classroom.setId(1);// Assuming the classroom ID is 1
//            Classroom_invite classroomInvite = new Classroom_invite();
//            classroomInvite.setClassrooms(classroom);
//            classroomInvite.setInvite_code("ABC123");
//            classroomInvite.setCreated_at(LocalDateTime.now());
//            classroomInvite.setExpire_at(LocalDateTime.now().plusDays(7));
//            classroomInvite.setIs_used(false);
//
//            // Test create_classroom_invitation method
//            boolean isClassroomInviteCreated = invitation_controller.create_classroom_invitation(classroomInvite);
//            System.out.println("Classroom invitation created: " + isClassroomInviteCreated);
//
//            // Test get_classroom_invitation method
//            Classroom_invite retrievedClassroomInvite = invitation_controller.get_classroom_invitation(1);// Assuming the invitation ID is 1
//            System.out.println("Retrieved Classroom Invitation: " + retrievedClassroomInvite);
//
//            // Create a new chatroom invitation
//            Chatrooms chatroom = new Chatrooms();
//            chatroom.setChartroom_id(1); // Assuming the chatroom ID is 1
//            Chatroom_invite chatroomInvite = new Chatroom_invite();
//            chatroomInvite.setChatrooms(chatroom);
//            chatroomInvite.setInvite_code("XYZ789");
//            chatroomInvite.setCreated_at(LocalDateTime.now());
//            chatroomInvite.setExpire_at(LocalDateTime.now().plusDays(7));
//            chatroomInvite.setIs_used(false);
//
//            // Test create_chatroom_invitation method
//            boolean isChatroomInviteCreated = invitation_controller.create_chatroom_invitation(chatroomInvite);
//            System.out.println("Chatroom invitation created: " + isChatroomInviteCreated);
//
//            // Test get_chatroom_invitation method
//            Chatroom_invite retrievedChatroomInvite = invitation_controller.get_chatroom_invitation(1);// Assuming the invitation ID is 1
//            System.out.println("Retrieved Chatroom Invitation: " + retrievedChatroomInvite);
//        }
//    }
}
