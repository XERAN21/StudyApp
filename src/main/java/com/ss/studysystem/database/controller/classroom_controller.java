package com.ss.studysystem.database.controller;

import com.ss.studysystem.Model.*;
import com.ss.studysystem.database.connection.DB_Connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class classroom_controller {
    public static boolean create_classroom(Classrooms classrooms) {
        String sql = "CALL create_classroom(?,?,?,?)";
        try (Connection connection = DB_Connection.Get_Connection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {

            callableStatement.setString(1, classrooms.getName());
            callableStatement.setString(2, classrooms.getDescription());
            callableStatement.setInt(3, classrooms.getUser().getId());
            callableStatement.setTimestamp(4, Timestamp.valueOf(classrooms.getCreated_at()));

            int row_affected = callableStatement.executeUpdate();
            callableStatement.close();
            return row_affected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Classrooms get_classroom(int classroom_id) {
        String sql = "CALL get_classroom(?)";
        Classrooms classrooms = new Classrooms();
        Users user = new Users();
        try (Connection connection = DB_Connection.Get_Connection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {

            callableStatement.setInt(1, classroom_id);

            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                user.setId(resultSet.getInt("created_by"));
                classrooms.setId(classroom_id);
                classrooms.setName(resultSet.getString("name"));
                classrooms.setDescription(resultSet.getString("description"));
                classrooms.setUser(user);
                classrooms.setCreated_at(resultSet.getTimestamp("created_at").toLocalDateTime());
                classrooms.set_archived(resultSet.getBoolean("is_archived"));

                callableStatement.close();
                resultSet.close();
                return classrooms;
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public static boolean add_member(User_Classroom user_classroom) {
        String sql = "CALL Add_member(?,?,?)";
        try (Connection connection = DB_Connection.Get_Connection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {

            callableStatement.setInt(1, user_classroom.getUser().getId());
            callableStatement.setInt(2, user_classroom.getClassrooms().getId());
            callableStatement.setString(3, user_classroom.getRole().toString());

            int row_affected = callableStatement.executeUpdate();

            callableStatement.close();
            return row_affected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //todo finish this controller
    public static boolean join_classroom(int invitation_code) {
        return true;
    }


    /**
     * @param classroom_id
     * @return users
     */

    public static List<User_Classroom> get_all_member(int classroom_id) {

        List<User_Classroom> member_list = new ArrayList<>();
        String sql = "CALL Get_all_members(?)";
        try (Connection connection = DB_Connection.Get_Connection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {

            callableStatement.setInt(1, classroom_id);
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                User_Classroom member = new User_Classroom();
                Users user = new Users();
                user.setId(resultSet.getInt("user_id"));
                user.setUsername(resultSet.getString("username"));
                member.setUser(user);

                Classrooms classroom = new Classrooms();
                classroom.setId(classroom_id);
                member.setClassrooms(classroom);

                String role = resultSet.getString("role").toUpperCase();
                member.setRole(Role.valueOf(role));

                member_list.add(member);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member_list;
    }


    public static boolean upload_file(Files files) {
        String sql = "CALL upload_file(?,?,?,?,?)";
        try (Connection connection = DB_Connection.Get_Connection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {

            callableStatement.setInt(1, files.getUploader().getId());
            callableStatement.setString(2, files.getFilename());
            callableStatement.setBlob(3, files.getFile_path());
            callableStatement.setString(4, files.getFile_type());
            callableStatement.setTimestamp(5, Timestamp.valueOf(files.getUploaded_at()));

            int row_affected = callableStatement.executeUpdate();
            return row_affected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delete_file(int file_id) {
        String sql = "CALL delete(?)";
        try (Connection connection = DB_Connection.Get_Connection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {

            callableStatement.setInt(1, file_id);

            int row_affected = callableStatement.executeUpdate();
            return row_affected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean update_classroom(Classrooms classrooms) {
        String sql = "CALL update_classroom(?,?,?,?)";
        try (Connection connection = DB_Connection.Get_Connection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {

            callableStatement.setInt(1, classrooms.getId());
            callableStatement.setString(2, classrooms.getName());
            callableStatement.setString(3, classrooms.getDescription());
            callableStatement.setBoolean(4, classrooms.is_archived());

            int row_affected = callableStatement.executeUpdate();
            return row_affected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Classrooms> get_all_classrooms(int user_id) {
        String sql = "CALL get_all_classrooms(?)";
        List<Classrooms> classrooms = new ArrayList<>();
        try (Connection connection = DB_Connection.Get_Connection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {

            callableStatement.setInt(1, user_id);

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Users users = new Users();
                users.setId(resultSet.getInt("student_id"));
                users.setUsername(resultSet.getString("student_username"));

                Users users1 = new Users();
                users1.setId(resultSet.getInt("created_by_id"));
                users1.setUsername(resultSet.getString("created_by_username"));

                Classrooms classroom = new Classrooms();
                classroom.setId(resultSet.getInt("classroom_id"));
                classroom.setName(resultSet.getString("classroom_name"));
                classroom.setDescription(resultSet.getString("classroom_description"));
                classroom.setUser(users1);

                classrooms.add(classroom);
            }

            return classrooms;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Classrooms> get_owned_classrooms(int user_id) {
        String sql = "SELECT * FROM classrooms where created_by = ?";
        List<Classrooms> list_classrooms = new ArrayList<>();
        try (Connection connection = DB_Connection.Get_Connection();
             PreparedStatement callableStatement = connection.prepareCall(sql)) {

            callableStatement.setInt(1, user_id);

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Classrooms classrooms = new Classrooms();
                classrooms.setId(resultSet.getInt("classroom_id"));
                classrooms.setName(resultSet.getString("name"));
                classrooms.setDescription(resultSet.getString("description"));
                classrooms.setCreated_at(resultSet.getTimestamp("created_at").toLocalDateTime());
                classrooms.set_archived(resultSet.getBoolean("is_archived"));

                list_classrooms.add(classrooms);
            }
            callableStatement.close();
            resultSet.close();
            return list_classrooms;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //todo testing

//    public class Main {
//        public static void main(String[] args) {
//
//            // Create a new user
//            Users user = new Users();
//            user.setId(1); // Assuming the user ID is 1
//
//            // Create a new classroom
//            Classrooms newClassroom = new Classrooms();
//            newClassroom.setName("Math 101");
//            newClassroom.setDescription("Basic Math Class");
//            newClassroom.setUser(user); newClassroom.setCreated_at(LocalDateTime.now());
//
//            // Test create_classroom method
//            boolean isClassroomCreated = classroom_controller.create_classroom(newClassroom);
//            System.out.println("Classroom created: " + isClassroomCreated);
//
//            // Test get_classroom method
//            Classrooms retrievedClassroom = classroom_controller.get_classroom(newClassroom.getId());
//            System.out.println("Retrieved Classroom: " + retrievedClassroom);
//
//            // Create a new user_classroom
//            User_Classroom newUserClassroom = new User_Classroom();
//            newUserClassroom.setUser(user);
//            newUserClassroom.setClassrooms(newClassroom);
//            newUserClassroom.setRole(Role.STUDENT);
//
//            // Test add_member method
//            boolean isMemberAdded = classroom_controller.add_member(newUserClassroom);
//            System.out.println("Member added: " + isMemberAdded);
//
//            // Test get_all_member method
//            List<User_Classroom> memberList = classroom_controller.get_all_member(newClassroom.getId());
//            System.out.println("Member List: " + memberList);
//
//            // Create a new file
//            Files newFile = new Files();
//            newFile.setUploader(user);
//            newFile.setFilename("math_notes.pdf");
//            newFile.setFile_path(null);// Assuming no file path for this example
//            newFile.setFile_type("pdf");
//            newFile.setUploaded_at(LocalDateTime.now());
//
//            // Test upload_file method
//            boolean isFileUploaded = classroom_controller.upload_file(newFile);
//            System.out.println("File uploaded: " + isFileUploaded);
//
//            // Test delete_file method
//            boolean isFileDeleted = classroom_controller.delete_file(newFile.getId());
//            System.out.println("File deleted: " + isFileDeleted);
//
//            // Update classroom details
//            newClassroom.setName("Advanced Math 101");
//            boolean isClassroomUpdated = classroom_controller.update_classroom(newClassroom);
//            System.out.println("Classroom updated: " + isClassroomUpdated);
//
//            // Test get_all_classrooms method
//            List<Classrooms> classroomsList = classroom_controller.get_all_classrooms(user.getId());
//            System.out.println("Classrooms List: " + classroomsList);
//        }
//    }
}