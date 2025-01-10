package com.ss.studysystem.database.controller;

import com.ss.studysystem.Model.Classrooms;
import com.ss.studysystem.Model.Test;
import com.ss.studysystem.Model.Test_Type;
import com.ss.studysystem.Model.Users;
import com.ss.studysystem.database.connection.DB_Connection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class test_controller {
    static boolean flag = true;
    public static boolean create_test(Test test){

        String sql = "CALL create_test(?,?,?,?,?,?,?)";
        try(Connection con = DB_Connection.Get_Connection();
            CallableStatement callableStatement = con.prepareCall(sql)) {

            callableStatement.setInt(1,test.getClassroom().getId());
            callableStatement.setString(2, test.getTitle());
            callableStatement.setString(3, test.getType().name());
            callableStatement.setInt(4,test.getUser().getId());
            callableStatement.setTimestamp(5, Timestamp.valueOf(test.getCreated_at()));
            callableStatement.setTimestamp(6,  Timestamp.valueOf(test.getStart_time()));
            callableStatement.setTimestamp(7, Timestamp.valueOf(test.getEnd_time()));

            int row_affected = callableStatement.executeUpdate();
            return row_affected>0 ;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public static Test get_test(int test_id){
        String sql = "CALL get_test(?)";
        Test test = new Test();
        try (Connection con = DB_Connection.Get_Connection();
            CallableStatement callableStatement = con.prepareCall(sql);){

            callableStatement.setInt(1, test_id);

            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                Users users = new Users();
                users.setId(resultSet.getInt("created_by"));

                Classrooms classrooms = new Classrooms();
                classrooms.setId(resultSet.getInt("classroom_id"));

                test.setTest(resultSet.getInt("test_id"));
                test.setClassroom(classrooms);
                test.setTitle(resultSet.getString("title"));
                test.setType(Test_Type.valueOf(resultSet.getString("type").toUpperCase()));
                test.setUser(users);
                test.setCreated_at(resultSet.getTimestamp("created_at").toLocalDateTime());
                test.setStart_time(resultSet.getTimestamp("start_time").toLocalDateTime());
                test.setEnd_time(resultSet.getTimestamp("end_time").toLocalDateTime());

            }
                return test;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public static List<Test> get_all_tests(){

        List<Test> test_list = new ArrayList<>();
        String sql = "CALL get_all_test()";

        try(Connection connection = DB_Connection.Get_Connection();
            CallableStatement callableStatement = connection.prepareCall(sql)) {


            ResultSet resultSet = callableStatement.executeQuery();


            while (resultSet.next()) {
                Test test = new Test();
                test.setTest(resultSet.getInt("test_id"));
              //  test.setClassroom(classroom_controller.get_classroom(resultSet.getInt("classroom_id")));
                test.setTitle(resultSet.getString("title"));
                test.setType(Test_Type.valueOf(resultSet.getString("type").toUpperCase()));
               // test.setUser(user_controller.get_user_by_id(resultSet.getInt("created_by"),flag));
                test.setCreated_at(resultSet.getTimestamp("created_at").toLocalDateTime());
                test_list.add(test);
            }
            return test_list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static boolean update_test(Test test){
        String sql = "CALL update_test(?,?,?,?,?,?,?,?)";

        try(Connection con = DB_Connection.Get_Connection();
            CallableStatement callableStatement = con.prepareCall(sql)) {

            callableStatement.setInt(1,test.getTest());
            callableStatement.setInt(2,test.getClassroom().getId());
            callableStatement.setString(3, test.getTitle());
            callableStatement.setString(4, test.getType().toString());
            callableStatement.setInt(5, test.getUser().getId());
            callableStatement.setTimestamp(6, Timestamp.valueOf(test.getCreated_at()));
            callableStatement.setTimestamp(7,  Timestamp.valueOf(test.getStart_time()));
            callableStatement.setTimestamp(8, Timestamp.valueOf(test.getEnd_time()));

            int row_affected = callableStatement.executeUpdate();
            return row_affected>0 ;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delete_test(int test_id){
        String sql = "CALL delete_test(?)";
        try(Connection con = DB_Connection.Get_Connection();
            CallableStatement callableStatement = con.prepareCall(sql)) {

            callableStatement.setInt(1,test_id);

            int row_affected = callableStatement.executeUpdate();
            return  row_affected > 0;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    //todo testing

//    public class Main {
//        public static void main(String[] args) {
//            // Create a new user
//            Users user = new Users();
//            user.setId(1);// Assuming the user ID is 1
//
//            // Create a new classroom
//            Classrooms classroom = new Classrooms();
//            classroom.setId(1); // Assuming the classroom ID is 1
//
//            // Create a new test
//            Test newTest = new Test();
//            newTest.setClassroom(classroom);
//            newTest.setTitle("Midterm Exam");
//            newTest.setType(Test_Type.MIDTERM);
//            newTest.setUser(user);
//            newTest.setCreated_at(LocalDateTime.now());
//            newTest.setStart_time(LocalDateTime.of(2025, 1, 15, 10, 0));
//            newTest.setEnd_time(LocalDateTime.of(2025, 1, 15, 12, 0));
//
//            // Test create_test method
//            boolean isTestCreated = test_controller.create_test(newTest);
//            System.out.println("Test created: " + isTestCreated);
//
//            // Test get_test method
//            Test retrievedTest = test_controller.get_test(newTest.getTest());
//            System.out.println("Retrieved Test: " + retrievedTest);
//
//            // Test get_all_tests method
//            List<Test> allTests = test_controller.get_all_tests();
//            System.out.println("All Tests: " + allTests);
//
//            // Update test details
//            newTest.setTitle("Updated Midterm Exam");
//            boolean isTestUpdated = test_controller.update_test(newTest);
//            System.out.println("Test updated: " + isTestUpdated);
//
//            // Test delete_test method
//            boolean isTestDeleted = test_controller.delete_test(newTest.getTest());
//            System.out.println("Test deleted: " + isTestDeleted);
//        }
//    }
}




