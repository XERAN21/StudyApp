package com.ss.studysystem.database.controller;

import com.ss.studysystem.Model.Classrooms;
import com.ss.studysystem.Model.To_Do_List;
import com.ss.studysystem.Model.Users;
import com.ss.studysystem.database.connection.DBConnection;
import com.ss.studysystem.Model.Assignments;
import com.ss.studysystem.database.connection.DB_Connection;
import com.sun.javafx.image.impl.BaseIntToIntConverter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class assignment_controller extends DBConnection<Assignments> {

    @Override
    public boolean save(Assignments entity) {

        String sql = "CALL create_assignment(?,?,?,?,?,?)";
        try(CallableStatement callableStatement = connection.prepareCall(sql)){

            callableStatement.setInt(1,entity.getClassroom().getId());
            callableStatement.setString(2,entity.getTitle());
            callableStatement.setString(3,entity.getDescription());
            callableStatement.setTimestamp(4, Timestamp.valueOf(String.valueOf(entity.getDue_date())));

            //todo revise uid
            // upload -> creation of assignment model
            callableStatement.setInt(5, entity.getCreated_by().getId());
            callableStatement.setTimestamp(6, Timestamp.valueOf(entity.getCreated_at()));

            int row_affected = callableStatement.executeUpdate();
            return row_affected > 0;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Assignments get(int id) {
        String sql = "CALL get_assignment()";
        Assignments assignments = new Assignments();

        try (Connection con = DB_Connection.Get_Connection();
             CallableStatement callableStatement = con.prepareCall(sql)) {


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
        try(Connection con = DB_Connection.Get_Connection();
            CallableStatement callableStatement = con.prepareCall(sql)){
            callableStatement.setInt(1, entity.getAssignment_id());
            callableStatement.setString(2, entity.getTitle());
            callableStatement.setString(3, entity.getDescription());
            callableStatement.setTimestamp(4, Timestamp.valueOf(String.valueOf(entity.getDue_date())));

            int row_affected = callableStatement.executeUpdate();
            return row_affected > 0;
        }catch(SQLException e ){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int uid) {
        String sql = "CALL delete_assignment(?)";
        try(Connection con = DB_Connection.Get_Connection();
            CallableStatement callableStatement = con.prepareCall(sql)){
            callableStatement.setInt(1,uid);

            int row_affected = callableStatement.executeUpdate();
            return  row_affected > 0;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     *
     * @param id -> classroom id
     * @return
     */
    @Override
    public List<Assignments> get_all_entity(int id) {
        String sql = "CALL get_all_entity_assignment(?)"; //todo fix procedure
        List<Assignments> assignments = new ArrayList<>();

        try (Connection con = DB_Connection.Get_Connection();
             CallableStatement callableStatement = con.prepareCall(sql)) {

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



}
