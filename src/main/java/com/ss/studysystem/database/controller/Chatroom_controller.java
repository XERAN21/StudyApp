package com.ss.studysystem.database.controller;

import com.ss.studysystem.Model.Chatrooms;
import com.ss.studysystem.Model.User_Chatroom;
import com.ss.studysystem.Model.Users;
import com.ss.studysystem.database.connection.DB_Connection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//todo Need to check PRIORITY HIGH
public class Chatroom_controller {

    public static List<Chatrooms> getAllChatrooms(int id) {
        String sql = "CALL Get_All_Chatrooms(?)";
        //fix procedures userid = ?

        List<Chatrooms> chatroomsList = new ArrayList<>();

        try (Connection con = DB_Connection.Get_Connection();
             CallableStatement callableStatement = con.prepareCall(sql)) {

//fix javacode set
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                Chatrooms chatroom = new Chatrooms();
                chatroom.setChartroom_id(resultSet.getInt("chatroom_id"));
                chatroom.setName(resultSet.getString("name"));
                chatroom.setInvitation_code(resultSet.getString("invitation_code"));
                chatroom.setCreated_at(resultSet.getTimestamp("created_at").toLocalDateTime());

                Users user = new Users();
                user.setId(resultSet.getInt("created_by"));
                chatroom.setUser(user);

                chatroomsList.add(chatroom);
            }

            return chatroomsList;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Create a new chatroom
    public static boolean create_Chatroom(Chatrooms chatroom) {
        String sql = "CALL Create_Chatroom(?, ?, ?)";
        try (Connection con = DB_Connection.Get_Connection();
             CallableStatement callableStatement = con.prepareCall(sql)) {

            callableStatement.setString(1, chatroom.getName());
            callableStatement.setInt(2, chatroom.getUser().getId());
            callableStatement.setString(3, chatroom.getInvitation_code());

            int rowsAffected = callableStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Add user to a chatroom
    public static boolean addUserToChatroom(User_Chatroom user_chatroom) {
        String sql = "CALL Add_User_To_Chatroom(?, ?)";
        try (Connection con = DB_Connection.Get_Connection();
             CallableStatement callableStatement = con.prepareCall(sql)) {

            callableStatement.setInt(1, user_chatroom.getUser().getId());
            callableStatement.setInt(2, user_chatroom.getChatroom().getChartroom_id());

            int rowsAffected = callableStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Remove user from a chatroom
    public static boolean removeUserFromChatroom(int userId, int chatroomId) {
        String sql = "CALL Remove_User_From_Chatroom(?, ?)";
        try (Connection con = DB_Connection.Get_Connection();
             CallableStatement callableStatement = con.prepareCall(sql)) {

            callableStatement.setInt(1, userId);
            callableStatement.setInt(2, chatroomId);

            int rowsAffected = callableStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all messages in a chatroom
    public static List<String> getChatroomMessages(int chatroomId) {
        String sql = "CALL Get_Chatroom_Messages(?)";
        List<String> messages = new ArrayList<>();

        try (Connection con = DB_Connection.Get_Connection();
             CallableStatement callableStatement = con.prepareCall(sql)) {

            callableStatement.setInt(1, chatroomId);

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                messages.add(resultSet.getString("message_text"));
            }

            return messages;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Delete a message by ID
    public static boolean delete_message(int messageId) {
        String sql = "CALL Delete_Message(?)";
        try (Connection con = DB_Connection.Get_Connection();
             CallableStatement callableStatement = con.prepareCall(sql)) {

            callableStatement.setInt(1, messageId);

            int rowsAffected = callableStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //todo create method get_all_member
    public static List<Users> getAllMembers(int chatroomId){
        String sql = "CALL Get_All_Members(?)";
        List<Users> members = new ArrayList<>();

        try (Connection con = DB_Connection.Get_Connection();
             CallableStatement callableStatement = con.prepareCall(sql)) {

            callableStatement.setInt(1, chatroomId);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Users user = new Users();
                user.setId(resultSet.getInt("user_id"));
                user.setUsername(resultSet.getString("username"));
//                user.setProfile_img(resultSet.getBlob("profile_img"));
                members.add(user);
            }
            return members;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        int chatroomId = 1;
        List<Users> members = getAllMembers(chatroomId);
        if(members != null) {
            for (Users user : members) {
                System.out.println("User ID: " + user.getId());
                System.out.println("Username: " + user.getUsername());
                System.out.println("Profile Image: " + user.getProfile_img());
            }
        }else{
                System.out.println("No members found or an error occurred.");
            }
        }
    }

