package com.ss.studysystem.database.controller;

import com.ss.studysystem.Model.Classrooms;
import com.ss.studysystem.Model.Users;
import com.ss.studysystem.database.connection.DBConnection;
import com.ss.studysystem.Model.Assignments;
import com.ss.studysystem.database.connection.DB_Connection;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class assignment_controller extends DBConnection<Assignments> {

    @Override
    public boolean save(Assignments entity) {

        String sql = "CALL create_assignment(?,?,?,?,?,?)";
        try (CallableStatement callableStatement = connection.prepareCall(sql)) {

            callableStatement.setInt(1, entity.getClassroom().getId());
            callableStatement.setString(2, entity.getTitle());
            callableStatement.setString(3, entity.getDescription());
            callableStatement.setDate(4, Date.valueOf(entity.getDue_date()));

            //todo revise uid
            // upload -> creation of assignment model
            callableStatement.setInt(5, entity.getCreated_by().getId());
            callableStatement.setTimestamp(6, Timestamp.valueOf(entity.getCreated_at()));

            int row_affected = callableStatement.executeUpdate();
            return row_affected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Assignments get(int id) {
        String sql = "CALL get_assignment(?)";
        Assignments assignments = new Assignments();

        try (CallableStatement callableStatement = connection.prepareCall(sql)) {

            callableStatement.setInt(1, id);
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                Classrooms classrooms = new Classrooms();
                classrooms.setId(resultSet.getInt("classroom_id"));

                Users users = new Users();
                users.setId(resultSet.getInt("created_by"));

                assignments.setAssignment_id(resultSet.getInt("assignment_id"));
                assignments.setClassroom(classrooms);

                assignments.setTitle(resultSet.getString("title"));

                assignments.setDescription(resultSet.getString("description"));

                assignments.setDue_date(resultSet.getDate("due_date").toLocalDate());

                assignments.setCreated_at(resultSet.getTimestamp("created_at").toLocalDateTime());
            }
            return assignments;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(Assignments entity) {
        String sql = "CALL update_assignment(?,?,?,?)";
        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
            callableStatement.setInt(1, entity.getAssignment_id());
            callableStatement.setString(2, entity.getTitle());
            callableStatement.setString(3, entity.getDescription());
            callableStatement.setTimestamp(4, Timestamp.valueOf(entity.getDue_date().atStartOfDay()));

            int row_affected = callableStatement.executeUpdate();
            return row_affected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "CALL delete_assignment(?)";
        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
            callableStatement.setInt(1, id);

            int row_affected = callableStatement.executeUpdate();
            return row_affected > 0;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * @param id -> classroom id
     * @return
     */
    @Override
    public List<Assignments> get_all_entity(int id) {
        String sql = "CALL get_all_assignment(?)"; //todo fix procedure
        List<Assignments> assignments = new ArrayList<>();

        try (CallableStatement callableStatement = connection.prepareCall(sql)) {

            callableStatement.setInt(1, id);

            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                Assignments assignment = new Assignments();
                Classrooms classrooms = new Classrooms();
                classrooms.setId(id);

                Users users = new Users();
                users.setId(resultSet.getInt("created_by"));

                assignment.setAssignment_id(resultSet.getInt("assignment_id"));
                assignment.setClassroom(classrooms);

                assignment.setTitle(resultSet.getString("title"));

                assignment.setDescription(resultSet.getString("description"));

                assignment.setDue_date(resultSet.getDate("due_date").toLocalDate());

                assignment.setCreated_at(resultSet.getTimestamp("created_at").toLocalDateTime());

                assignments.add(assignment);
            }
            return assignments;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //todo testing

//    public class Main {
//        public static void main(String[] args) {
//            // Create a new user
//            Users user = new Users();
//            user.setId(1); // Assuming the user ID is 1
//
//            // Create a new classroom
//            Classrooms classroom = new Classrooms();
//            classroom.setId(1); // Assuming the classroom ID is 1
//
//            // Create a new assignment
//            Assignments newAssignment = new Assignments();
//            newAssignment.setClassroom(classroom);
//            newAssignment.setTitle("Math Homework");
//            newAssignment.setDescription("Complete the exercises on page 42");
//            newAssignment.setDue_date(LocalDate.of(2025, 1, 15));
//            newAssignment.setCreated_by(user);
//            newAssignment.setCreated_at(LocalDateTime.now());
//
//            // Test save method
//            assignment_controller assignmentController = new assignment_controller();
//            boolean isAssignmentSaved = assignmentController.save(newAssignment);
//            System.out.println("Assignment saved: " + isAssignmentSaved);
//
//            // Test get method
//            Assignments retrievedAssignment = assignmentController.get(newAssignment.getAssignment_id());
//            System.out.println("Retrieved Assignment: " + retrievedAssignment);
//
//            // Update assignment details
//            newAssignment.setTitle("Updated Math Homework");
//            boolean isAssignmentUpdated = assignmentController.update(newAssignment);
//            System.out.println("Assignment updated: " + isAssignmentUpdated);
//
//            // Test get_all_entity method
//            List<Assignments> assignmentsList = assignmentController.get_all_entity(classroom.getId());
//            System.out.println("Assignments List: " + assignmentsList);
//
//            // Test delete method
//            boolean isAssignmentDeleted = assignmentController.delete(newAssignment.getAssignment_id());
//            System.out.println("Assignment deleted: " + isAssignmentDeleted);
//        }
//    }
}
