package com.ss.studysystem.database.controller;

import com.ss.studysystem.Model.Gender;
import com.ss.studysystem.Model.Users;
import com.ss.studysystem.auth.auth_manager;
import com.ss.studysystem.database.connection.DB_Connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class user_controller {

    public static boolean create_user(Users user) {
        String sql = "CALL Create_User(?,?,?,?,?,?)";
        try (Connection con = DB_Connection.Get_Connection();
             CallableStatement callableStatement = con.prepareCall(sql)) {

            callableStatement.setString(1, user.getUsername());
            callableStatement.setString(2, user.getEmail());
            callableStatement.setString(3, user.getPassword());
            callableStatement.setString(4, user.getSalt());
            callableStatement.setDate(5, Date.valueOf(user.getDob()));
            callableStatement.setString(6, user.getGender().toString());

            int rowsAffected = callableStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static Users search_user(String search_user) {
        /*
         * login -> valid credentials -> get_user_by_id
         * i.e. assing this.session.user  via get user
         *
         * */
        String sql = "CALL GetUser(?)";
        Users current_user = new Users();
        try (Connection con = DB_Connection.Get_Connection();
             CallableStatement callableStatement = con.prepareCall(sql)) {

            callableStatement.setString(1, search_user);

            ResultSet resultSet = callableStatement.executeQuery();

            if (resultSet.next()) {

                current_user.setId(resultSet.getInt("user_id"));
                current_user.setUsername(resultSet.getString("username"));
                current_user.setProfile_img(resultSet.getBlob("profile_img"));
                current_user.setStudy_hour(resultSet.getInt("total_study_hours"));
                current_user.setGender(Gender.valueOf(resultSet.getString("gender")));

                return current_user;
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return null;
    }


    public static Users login(String uid, String password) {
        String sql = "CALL GetUser(?)";
        Users current_user = new Users();
        try (Connection con = DB_Connection.Get_Connection();
             CallableStatement callableStatement = con.prepareCall(sql)) {

            callableStatement.setString(1, uid);

            ResultSet resultSet = callableStatement.executeQuery();

            if (resultSet.next()) {
                String hashed = resultSet.getString("password");
                String salt =  resultSet.getString("salt");
                try {
                    if(auth_manager.verify_password(password, hashed, salt)) {

                        current_user.setId(resultSet.getInt("user_id"));
                        current_user.setUsername(resultSet.getString("username"));
                        current_user.setProfile_img(resultSet.getBlob("profile_img"));
                        current_user.setStudy_hour(resultSet.getInt("total_study_hours"));
                        current_user.setGender(Gender.valueOf(resultSet.getString("gender").toUpperCase()));
                        current_user.setEmail(resultSet.getString("email"));
                        current_user.setPassword(resultSet.getString("password"));
                        current_user.setSalt(resultSet.getString("salt"));
                        current_user.setDob(resultSet.getDate("dob").toLocalDate());
                        current_user.setFile_patch(resultSet.getString("file_path"));
                        current_user.setIs_active(resultSet.getBoolean("is_active"));
                        current_user.setCreated_at(resultSet.getTimestamp("created_at").toLocalDateTime());

                        return current_user;
                    }
                    throw new Throwable("Incorrect Password");
                } catch (Throwable pw_err) {
                    throw new RuntimeException(pw_err);
                }
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return null;

    }

    public static Users get_user_by_username(String username) {
        String sql = "select * from users where username = ? ";
        Users users = new Users();

        try (Connection connection = DB_Connection.Get_Connection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                users.setId(resultSet.getInt("user_id"));
                users.setUsername(username);
                users.setEmail(resultSet.getString("email"));
                users.setDob(resultSet.getDate("dob").toLocalDate());
                users.setProfile_img(resultSet.getBlob("profile_img"));
                users.setStudy_hour(resultSet.getInt("total_study_hours"));
                users.setFile_patch(resultSet.getString("file_path"));
                users.setIs_active(resultSet.getBoolean("is_active"));
                users.setCreated_at(resultSet.getTimestamp("created_at").toLocalDateTime());
                users.setGender(Gender.valueOf(resultSet.getString("gender")));
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Users get_user_by_email(String email) {
        String sql = "select * from users where email = ? ";
        Users users = new Users();

        try (Connection connection = DB_Connection.Get_Connection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                users.setId(resultSet.getInt("user_id"));
                users.setUsername(resultSet.getString("username"));
                users.setEmail(email);
                users.setDob(resultSet.getDate("dob").toLocalDate());
                users.setProfile_img(resultSet.getBlob("profile_img"));
                users.setStudy_hour(resultSet.getInt("total_study_hours"));
                users.setFile_patch(resultSet.getString("file_path"));
                users.setIs_active(resultSet.getBoolean("is_active"));
                users.setCreated_at(resultSet.getTimestamp("created_at").toLocalDateTime());
                users.setGender(Gender.valueOf(resultSet.getString("gender")));
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Users> get_all_friends(int uid) {

        List<Users> friends_list = new ArrayList<>();
        String sql = "{CALL Get_all_friends(?)}";

        try (Connection con = DB_Connection.Get_Connection();
             CallableStatement callableStatement = con.prepareCall(sql)) {

            callableStatement.setInt(1, uid);
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                Users friend = new Users();
                friend.setId(resultSet.getInt("friend_id"));
                friend.setUsername(resultSet.getString("username"));
                friend.setEmail(resultSet.getString("email"));
                friend.setProfile_img(resultSet.getBlob("profile_img"));
                friends_list.add(friend);
            }

            return friends_list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean update_user(Users user) {
        String sql = "CALL Update_user_info(?,?,?,?,?,?,?,?)";
        try (Connection con = DB_Connection.Get_Connection();
             CallableStatement callableStatement = con.prepareCall(sql)) {

            callableStatement.setInt(1, user.getId());
            callableStatement.setString(2, user.getUsername());
            callableStatement.setString(3, user.getEmail());
            callableStatement.setString(4, user.getPassword());
            callableStatement.setString(5, user.getSalt());
            callableStatement.setDate(6, Date.valueOf(user.getDob()));
            callableStatement.setBlob(7, user.getProfile_img());
            callableStatement.setString(8, user.getGender().toString());

            int row_affected = callableStatement.executeUpdate();
            return row_affected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delete_user(int uid) {
        String sql = "CALL DeleteUser(?)";
        try (Connection con = DB_Connection.Get_Connection();
             CallableStatement callableStatement = con.prepareCall(sql)) {

            callableStatement.setInt(1, uid);

            int row_affected = callableStatement.executeUpdate();
            return row_affected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
