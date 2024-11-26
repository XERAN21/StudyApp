package com.ss.studysystem.database.controller;

import com.ss.studysystem.Model.Classrooms;
import com.ss.studysystem.Model.Role;
import com.ss.studysystem.Model.User_Classroom;

import com.ss.studysystem.Model.Users;
import com.ss.studysystem.database.connection.DB_Connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class classroom_controller {

    public static boolean create_classroom(Classrooms classrooms){
        String sql = "CALL create_classroom(?,?,?,?)";
        try(Connection connection = DB_Connection.Get_Connection();
            CallableStatement callableStatement = connection.prepareCall(sql)){

            callableStatement.setString(1,classrooms.getName());
            callableStatement.setString(2,classrooms.getDescription());
            callableStatement.setInt(3,classrooms.getUser().getId());
            callableStatement.setTimestamp(4, Timestamp.valueOf(classrooms.getCreated_at()));

            int row_affected = callableStatement.executeUpdate();
            return row_affected > 0;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public static boolean add_member(User_Classroom user_classroom){
        String sql = "CALL Add_member(?,?,?)";
        try(Connection connection = DB_Connection.Get_Connection();
            CallableStatement callableStatement = connection.prepareCall(sql)){

            callableStatement.setInt(1,user_classroom.getUser().getId());
            callableStatement.setInt(2,user_classroom.getClassrooms().getId());
            callableStatement.setString(3,user_classroom.getRole().toString());

            int row_affected = callableStatement.executeUpdate();
            return row_affected > 0;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * @param classroom_id
     * @return users
     */
    public static List<User_Classroom> get_all_member(int classroom_id){

        List<User_Classroom> member_list = new ArrayList<>();
        String sql = "CALL Get_all_members(?)";
        try(Connection connection = DB_Connection.Get_Connection();
            CallableStatement callableStatement = connection.prepareCall(sql)){

            callableStatement.setInt(1, classroom_id);
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()){
                User_Classroom member = new User_Classroom();

                Users user = new Users();
                user.setId(resultSet.getInt("user_id"));
                user.setUsername(resultSet.getString("username"));
                member.setUser(user);

                Classrooms classroom = new Classrooms();
                classroom.setId(resultSet.getInt("classroom_id"));
                member.setClassrooms(classroom);

                String role = resultSet.getString("role").toUpperCase();
                member.setRole(Role.valueOf(role));

                member_list.add(member);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return member_list;
    }
}
