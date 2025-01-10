package com.ss.studysystem.database.controller;

import com.ss.studysystem.Model.Classrooms;
import com.ss.studysystem.Model.Events;
import com.ss.studysystem.Model.Users;
import com.ss.studysystem.database.connection.DB_Connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class event_ctr {
    private static Connection connection;

    public event_ctr(Connection connection) {
        this.connection = connection;
    }

    public void createEvent(int eventId, String title, String description, Timestamp eventDate, int createdBy, int classroomId, Timestamp createdAt) throws SQLException {
        String query = "{CALL create_event(?, ?, ?, ?, ?, ?, ?)}";
        try (Connection connection = DB_Connection.Get_Connection();
             CallableStatement stmt = connection.prepareCall(query)) {
            stmt.setInt(1, eventId);
            stmt.setString(2, title);
            stmt.setString(3, description);
            stmt.setTimestamp(4, eventDate);
            stmt.setInt(5, createdBy);
            stmt.setInt(6, classroomId);
            stmt.setTimestamp(7, createdAt);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Events getEvent(int eventId) throws SQLException {
        String query = "{CALL get_event(?)}";
        try(CallableStatement stmt = connection.prepareCall(query)) {
            stmt.setInt(1, eventId);
            ResultSet rs = stmt.executeQuery();

            Users users = new Users();
            users.setId(rs.getInt("createdBy"));

            Classrooms classrooms = new Classrooms();
            classrooms.setId(rs.getInt("classroomId"));
            if (rs.next()) {
                Events e = new Events();
                e.setId(rs.getInt("eventId"));
                e.setTitle(rs.getString("title"));
                e.setDescription(rs.getString("description"));
                e.setEvent_date(rs.getTimestamp("eventDate").toLocalDateTime());
                e.setUser(users);
                e.setClassroom(classrooms);
                e.setCreated_at(rs.getTimestamp("createdAt").toLocalDateTime());
                return e;
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }
        return null;
    }

    public List<Events> getAllEvent() throws SQLException {
        List<Events> eventsList = new ArrayList<>();
        String query = "{CALL get_all_event()}";
        try (CallableStatement stmt = connection.prepareCall(query)) {
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
                e.setEvent_date(rs.getTimestamp("eventDate").toLocalDateTime());
                e.setUser(user);
                e.setClassroom(classrooms);
                e.setCreated_at(rs.getTimestamp("createdAt").toLocalDateTime());
                eventsList.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return eventsList;
    }

    public ResultSet getTodayEvent() throws SQLException {
        String query = "{CALL get_today_event()}";
        CallableStatement stmt = connection.prepareCall(query);
        return stmt.executeQuery();
    }

    public ResultSet getWeekEvent() throws SQLException {
        String query = "{CALL get_week_event()}";
        CallableStatement stmt = connection.prepareCall(query);
        return stmt.executeQuery();
    }

    public ResultSet getMonthEvent() throws SQLException {
        String query = "{CALL get_month_event()}";
        CallableStatement stmt = connection.prepareCall(query);
        return stmt.executeQuery();
    }

    public void updateEvent(int eventId, String title, String description, Timestamp eventDate, int createdBy, int classroomId, Timestamp createdAt) throws SQLException {
        String query = "{CALL update_event(?,?,?,?,?,?,?)}";
        try (CallableStatement stmt = connection.prepareCall(query)) {
            stmt.setInt(1, eventId);
            stmt.setString(2, title);
            stmt.setString(3, description);
            stmt.setTimestamp(4, eventDate);
            stmt.setInt(5, createdBy);
            stmt.setInt(6, classroomId);
            stmt.setTimestamp(7,createdAt);
            stmt.execute();
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }
    }

    public void deleteEvent(int eventId) throws SQLException {
        String query = "{CALL delete_event(?)}";
        try (CallableStatement stmt = connection.prepareCall(query)) {
            stmt.setInt(1, eventId);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    //todo testing

//        public static void main(String[] args) {
//            try (Connection connection = DB_Connection.Get_Connection()){
//                 event_ctr eventController = new event_ctr(connection);
//
//                 //Create a new event
//                 eventController.createEvent(1, "Event Title", "Event Description", Timestamp.valueOf("2025-01-06 14:00:00"), 1, 1, Timestamp.valueOf("2025-01-06 13:00:00"));
//                 Events event = eventController.getEvent(1);
//                if (event != null) {
//                System.out.println("Event ID: " + event.getId());
//                System.out.println("Title: " + event.getTitle());
//                System.out.println("Description: " + event.getDescription());
//                System.out.println("Event Date: " + event.getEvent_date());
//                System.out.println("Created By: " + event.getUser());
//                System.out.println("Classroom ID: " + event.getClassroom());
//                System.out.println("Created At: " + event.getCreated_at());
//            }
//
//                // Get all events
//                List<Events> allEvents = eventController.getAllEvent();
//                System.out.println("All Events: " + allEvents);
//
//                // Get today's events
//                ResultSet todayEvents = eventController.getTodayEvent();
//                while (todayEvents.next()) {
//                    System.out.println("Today's Event ID: " + todayEvents.getInt("eventId"));
//                    System.out.println("Title: " + todayEvents.getString("title"));
//                }
//
//                // Get this week's events
//                ResultSet weekEvents = eventController.getWeekEvent();
//                while (weekEvents.next()) {
//                    System.out.println("This Week's Event ID: " + weekEvents.getInt("eventId"));
//                    System.out.println("Title: " + weekEvents.getString("title"));
//                }
//
//                // Get this month's events
//                ResultSet monthEvents = eventController.getMonthEvent();
//                while (monthEvents.next()) {
//                    System.out.println("This Month's Event ID: " + monthEvents.getInt("eventId"));
//                    System.out.println("Title: " + monthEvents.getString("title"));
//                }
//
//                // Update the event
//                eventController.updateEvent(1, "Updated Event Title", "Updated Event Description", Timestamp.valueOf("2025-01-06 15:00:00"), 1, 1, Timestamp.valueOf("2025-01-06 13:00:00"));
//                Events updatedEvent = eventController.getEvent(1);
//                if (updatedEvent != null) {
//                    System.out.println("Updated Event ID: " + updatedEvent.getId());
//                    System.out.println("Updated Title: " + updatedEvent.getTitle());
//                    System.out.println("Updated Description: " + updatedEvent.getDescription());
//                    System.out.println("Updated Event Date: " + updatedEvent.getEvent_date());
//                }
//
//                // Delete the event
//                eventController.deleteEvent(1);
//                Events deletedEvent = eventController.getEvent(1);
//                if (deletedEvent == null) {
//                    System.out.println("Event successfully deleted.");
//                }
//        } catch (SQLException e){
//            e.printStackTrace();
//        }
//    }
}
