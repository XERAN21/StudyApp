package com.ss.studysystem.database.controller;

import com.ss.studysystem.Model.Questions;
import com.ss.studysystem.Model.Users;
import com.ss.studysystem.database.connection.DB_Connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class question_controller {

    public static boolean create_question(Questions questions) {
        String sql = "CALL create_question(?,?,?)";
        try (Connection connection = DB_Connection.Get_Connection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {

            callableStatement.setInt(1, questions.getId());
            callableStatement.setString(2, questions.getQuestion_text());
            callableStatement.setString(3, String.valueOf(questions.getQuestion_img()));

            int row_affected = callableStatement.executeUpdate();
            return row_affected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Questions get_question(int id) {
        String sql = "CALL get_question(?)";
        Questions questions = new Questions();
        try (Connection con = DB_Connection.Get_Connection();
             CallableStatement callableStatement = con.prepareCall(sql)) {

            callableStatement.setInt(1, id);

            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                questions.setId(resultSet.getInt("question_id"));
                questions.setQuestion_text(resultSet.getString("question_text"));
                questions.setQuestion_img(resultSet.getBlob("question_img"));
            }
            return questions;
        } catch (SQLException e1) {
            e1.printStackTrace();
            return null;
        }

    }
//todo need to check this one
    public static List<Questions> get_all_questions(int id) {
        List<Questions> Questions_list = new ArrayList<>();
        String sql = "{CALL get_all_questions(?)}";

        try (Connection con = DB_Connection.Get_Connection();
             CallableStatement callableStatement = con.prepareCall(sql)) {

            callableStatement.setInt(1, id);
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                Questions questions = new Questions();

                questions.setId(resultSet.getInt("question_id"));
                questions.setQuestion_text(resultSet.getString("question_text"));
                questions.setQuestion_img(resultSet.getBlob("question_img"));
                Questions_list.add(questions);
            }
            return Questions_list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static boolean update_question(Questions questions) {
        String sql = "CALL update_question(?,?,?)";
        try (Connection connection = DB_Connection.Get_Connection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {

            callableStatement.setInt(1, questions.getId());
            callableStatement.setString(2, questions.getQuestion_text());
            callableStatement.setBlob(3, questions.getQuestion_img());

            int row_affected = callableStatement.executeUpdate();
            return row_affected > 0;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }

    public static boolean delete_question(int id){
        String sql = "CALL delete_questions(?)";
        try(Connection connection = DB_Connection.Get_Connection();
            CallableStatement callableStatement = connection.prepareCall(sql)){
            callableStatement.setInt(1,id);

            int row_affected = callableStatement.executeUpdate();
            return row_affected > 0;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        Questions questions = new Questions();
        questions.setId(1);
        questions.setQuestion_text("Hello!!!!");
        System.out.println(delete_question(questions.getId()));
    }
}


