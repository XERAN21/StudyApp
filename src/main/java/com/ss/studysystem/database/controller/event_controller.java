package com.ss.studysystem.database.controller;

import com.ss.studysystem.Model.Classrooms;
import com.ss.studysystem.Model.Events;
import com.ss.studysystem.Model.Users;
import com.ss.studysystem.database.connection.DB_Connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class event_controller {
    private static Connection connection;

    public event_controller(Connection connection) {
        this.connection = connection;
    }

    public static boolean create_event(Events events) {
        String query = "{CALL create_event(?, ?, ?, ?, ?, ?, ?, ?)}";
        try (Connection connection = DB_Connection.Get_Connection();
             CallableStatement callableStatement = connection.prepareCall(query)) {

            callableStatement.setInt(1, events.getId());
            callableStatement.setString(2, events.getTitle());
            callableStatement.setString(3, events.getDescription());
            callableStatement.setTimestamp(4, Timestamp.valueOf(events.getCreated_at()));
            callableStatement.setInt(5, events.getUser().getId());
            callableStatement.setInt(6, events.getClassroom().getId());
            callableStatement.setTimestamp(7, Timestamp.valueOf(events.getCreated_at()));
            callableStatement.setTimestamp(8, Timestamp.valueOf(events.getStart_date()));

            int row_affected = callableStatement.executeUpdate();
            return row_affected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Events get_event(Events events) {
        String query = "{CALL get_event(?)}";
        Events event = new Events();
        try(CallableStatement stmt = connection.prepareCall(query)) {
            stmt.setInt(1, events.getId());
            ResultSet rs = stmt.executeQuery();

            Users users = new Users();
            users.setId(rs.getInt("createdBy"));

            Classrooms classrooms = new Classrooms();
            classrooms.setId(rs.getInt("classroomId"));
            if (rs.next()) {

                event.setId(rs.getInt("eventId"));
                event.setTitle(rs.getString("title"));
                event.setDescription(rs.getString("description"));
                event.setStart_date(rs.getTimestamp("start_date").toLocalDateTime());
                event.setUser(users);
                event.setClassroom(classrooms);
                event.setCreated_at(rs.getTimestamp("createdAt").toLocalDateTime());

            }
            return event;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public static List<Events> get_all_event(int uid) {
        List<Events> eventsList = new ArrayList<>();
        String query = "{CALL get_all_event(?)}";
        try (CallableStatement stmt = connection.prepareCall(query)) {
            stmt.setInt(1,uid);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Events e = new Events();

                Users user = new Users();
                user.setId(rs.getInt("createdBy"));

                Classrooms classrooms = new Classrooms();
                classrooms.setId(rs.getInt("classroomId"));

                e.setId(rs.getInt("eventId"));
                e.setTitle(rs.getString("title"));
                e.setDescription(rs.getString("description"));
                e.setStart_date(rs.getTimestamp("start_date").toLocalDateTime());
                e.setUser(user);
                e.setClassroom(classrooms);
                e.setCreated_at(rs.getTimestamp("createdAt").toLocalDateTime());
                eventsList.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return eventsList;
    }

    public ResultSet get_today_event(int uid) throws SQLException {
        String type = "TODAY";
        String query = "{CALL get_today_event()}";
        CallableStatement stmt = connection.prepareCall(query);
        stmt.setInt(1,uid);
        return stmt.executeQuery();
    }

    public ResultSet get_week_event(int uid) throws SQLException {
        String type = "WEEK";
        String query = "{CALL get_week_event()}";
        CallableStatement stmt = connection.prepareCall(query);
        stmt.setInt(1,uid);
        return stmt.executeQuery();


    }

    public ResultSet get_month_event(int uid) throws SQLException {
        String type = "MONTH";
        String query = "{CALL get_month_event()}";
        CallableStatement stmt = connection.prepareCall(query);
        stmt.setInt(1,uid);
        return stmt.executeQuery();
    }

    public void update_event(Events events) throws SQLException {
        String query = "{CALL update_event(?,?,?,?,?,?,?,?)}";
        try (CallableStatement stmt = connection.prepareCall(query)) {
            stmt.setInt(1, events.getId());
            stmt.setString(2, events.getTitle());
            stmt.setString(3, events.getDescription());
            stmt.setInt(4, events.getUser().getId());
            stmt.setInt(5, events.getClassroom().getId());
            stmt.setTimestamp(6, Timestamp.valueOf(events.getCreated_at()));
            stmt.setBoolean(7, events.is_archived());
            stmt.setTimestamp(8, Timestamp.valueOf(events.getStart_date()));
            stmt.execute();
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }
    }

    public void delete_event(int eventId) throws SQLException {
        String query = "{CALL delete_event(?)}";
        try (CallableStatement stmt = connection.prepareCall(query)) {
            stmt.setInt(1, eventId);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

}
