package com.ss.studysystem.database.controller;

import com.ss.studysystem.Model.Users;
import com.ss.studysystem.database.connection.DB_Connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class user_controller {

    public static boolean create_user(Users user){
        String sql = "CALL CreateUser(?,?,?,?)";
        try(Connection con = DB_Connection.Get_Connection();
            CallableStatement callableStatement = con.prepareCall(sql)){

            callableStatement.setString(1, user.getUsername());
            callableStatement.setString(2,user.getEmail());
            callableStatement.setString(3,user.getPassword());
            callableStatement.setDate(4, Date.valueOf(user.getDob()));

            int rowsAffected = callableStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Users get_user(int uid){
        /*
        * login -> valid credentials -> get_user
        * i.e. assing this.session.user  via get user
        *
        * */
        String sql = "CALL GetUser(?)";
        try(Connection con = DB_Connection.Get_Connection();
            CallableStatement callableStatement = con.prepareCall(sql)){

            callableStatement.setInt(1,uid);

            ResultSet resultSet = callableStatement.executeQuery();

            if (resultSet.next()){
                Users current_user = new Users();
                current_user.setId(resultSet.getInt("user_id"));
                current_user.setUsername(resultSet.getString("username"));
                current_user.setEmail(resultSet.getString("email"));
                current_user.setPassword(resultSet.getString("password"));
                current_user.setSalt(resultSet.getString("salt"));
                current_user.setDob(resultSet.getDate("dob").toLocalDate());
                current_user.setProfile_img(resultSet.getBlob("profile_img"));
                current_user.setFile_patch(resultSet.getString("file_path"));
                current_user.setStudy_hour(resultSet.getInt("total_study_hour"));
                current_user.setIs_active(resultSet.getBoolean("is active"));
                current_user.setCreated_at(resultSet.getTimestamp("created_at").toLocalDateTime());

                con.close();
                resultSet.close();
                return current_user;
            }

        }catch (SQLException e1){
            e1.printStackTrace();
        }
        return null;
    }

    public static List<Users> get_all_friends(int uid){

        List<Users> friends_list = new ArrayList<>();
        String sql = "{CALL Get_all_friends(?)}";

        try (Connection con = DB_Connection.Get_Connection();
             CallableStatement callableStatement = con.prepareCall(sql)){

            callableStatement.setInt(1, uid);
            ResultSet resultSet = callableStatement.executeQuery();

            while(resultSet.next()){
                Users friend = new Users();
                friend.setId(resultSet.getInt("friend_id"));
                friend.setUsername(resultSet.getString("username"));
                friend.setEmail(resultSet.getString("email"));
                friend.setProfile_img(resultSet.getBlob("profile_img"));
                friends_list.add(friend);
            }

            return  friends_list;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static boolean update_user(Users user){
        String sql = "CALL Update_user_info(?,?,?,?,?,?,?)";
        try(Connection con = DB_Connection.Get_Connection();
            CallableStatement callableStatement = con.prepareCall(sql)){

            callableStatement.setInt(1,user.getId());
            callableStatement.setString(2,user.getUsername());
            callableStatement.setString(3,user.getEmail());
            callableStatement.setString(4,user.getPassword());
            callableStatement.setString(5, user.getSalt());
            callableStatement.setDate(6,Date.valueOf(user.getDob()));
            callableStatement.setBlob(7,user.getProfile_img());

            int row_affected = callableStatement.executeUpdate();
            return row_affected > 0;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delete_user(int uid){
        String sql = "CALL DeleteUser(?)";
        try(Connection con = DB_Connection.Get_Connection();
            CallableStatement callableStatement = con.prepareCall(sql)){

            callableStatement.setInt(1,uid);

            int row_affected = callableStatement.executeUpdate();
            return row_affected > 0;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

}
