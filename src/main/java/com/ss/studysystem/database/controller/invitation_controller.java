package com.ss.studysystem.database.controller;

import com.ss.studysystem.Model.Chatroom_invite;
import com.ss.studysystem.Model.Chatrooms;
import com.ss.studysystem.Model.Classroom_invite;
import com.ss.studysystem.Model.Classrooms;
import com.ss.studysystem.database.connection.DB_Connection;

import java.sql.*;

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
}
