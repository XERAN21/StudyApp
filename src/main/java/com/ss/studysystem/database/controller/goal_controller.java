package com.ss.studysystem.database.controller;

import com.ss.studysystem.Model.Goals;
import com.ss.studysystem.Model.Users;
import com.ss.studysystem.Model.status;
import com.ss.studysystem.database.connection.DB_Connection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//todo check all code and test it
public class goal_controller {

    public static boolean create_goal(Goals goals) {
        String sql = "CALL Create_goal(?,?,?,?,?)";
        try (Connection connection = DB_Connection.Get_Connection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {

            callableStatement.setInt(1, goals.getUser().getId());
            callableStatement.setString(2, goals.getDescription());
            callableStatement.setDate(3, Date.valueOf(goals.getTarget_date()));
            callableStatement.setString(4, goals.getStatus().toString());
            callableStatement.setTimestamp(5, Timestamp.valueOf(goals.getCreated_at()));

            int row_affected = callableStatement.executeUpdate();
            return row_affected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Goals> get_goal(int uid) {
        // using uid to show all of his/her goals
        String sql = "CALL get_goals(?)";
        List<Goals> goals = new ArrayList<>();
        try (Connection connection = DB_Connection.Get_Connection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {

            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                Goals goal = new Goals();
                Users u = new Users();
                u.setId(resultSet.getInt(uid));

                goal.setGoal(resultSet.getInt("goal_id"));
                goal.setUser(u); // setting user Id into goal
                goal.setDescription(resultSet.getString("description"));
                goal.setTarget_date(LocalDate.parse(String.valueOf(resultSet.getDate("target_date"))));
                goal.setStatus(status.valueOf(resultSet.getString("status")));
                goal.setCreated_at(resultSet.getTimestamp("Created_at").toLocalDateTime());
            }
            return goals;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        }
    }

    public static boolean updateGoal(Goals goals) {
        String sql = "CALL update_goals(?, ?, ?, ?, ?)";
        try (Connection connection = DB_Connection.Get_Connection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {

            callableStatement.setInt(1, goals.getGoal());
            callableStatement.setString(2, goals.getDescription());
            callableStatement.setDate(3, Date.valueOf(goals.getTarget_date()));
            callableStatement.setString(4, goals.getStatus().toString());
            callableStatement.setTimestamp(5, Timestamp.valueOf(goals.getCreated_at()));


            int rowsAffected = callableStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        }

    }

    public static boolean deleteGoal(int goalId) {
        String sql = "CALL delete_goals(?)";

        try (Connection connection = DB_Connection.Get_Connection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {
            callableStatement.setInt(1, goalId);

            int rowsAffected = callableStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;


        }
    }
}