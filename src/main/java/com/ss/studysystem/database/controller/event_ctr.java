//package com.ss.studysystem.database.controller;
//
//import com.ss.studysystem.Model.Events;
//import com.ss.studysystem.database.connection.DB_Connection;
//
//import java.sql.*;
//
//public class event_ctr {
//    private Connection connection;
//
//    public event_ctr(Connection connection) {
//        this.connection = connection;
//    }
//
//    public static void createEvent(int eventId, String title, String description, Timestamp eventDate, int createdBy, int classroomId, Timestamp createdAt) throws SQLException {
//        String query = "{CALL create_event(?, ?, ?, ?, ?, ?, ?)}";
//        try (Connection connection = DB_Connection.Get_Connection();
//             CallableStatement stmt = connection.prepareCall(query)){
//            stmt.setInt(1, eventId);
//            stmt.setString(2, title);
//            stmt.setString(3, description);
//            stmt.setTimestamp(4, eventDate);
//            stmt.setInt(5, createdBy);
//            stmt.setInt(6, classroomId);
//            stmt.setTimestamp(7, createdAt);
//            stmt.execute();
//        }
//    }
//
//    public static Events getEvent(int eventId) throws SQLException {
//        String query = "{CALL get_event(?)}";
//        CallableStatement stmt = connection.prepareCall(query);
//        stmt.setInt(1, eventId);
//        ResultSet rs = stmt.executeQuery();
//        while (rs.next()){
//            Events e = new Events();
//            e.setId();
//
//            return Events;
//        }
//        return null;
//    }
//
//    public ResultSet getTodayEvent() throws SQLException {
//        String query = "{CALL get_today_event()}";
//        CallableStatement stmt = connection.prepareCall(query);
//        return stmt.executeQuery();
//    }
//
//    public ResultSet getWeekEvent() throws SQLException {
//        String query = "{CALL get_week_event()}";
//        CallableStatement stmt = connection.prepareCall(query);
//        return stmt.executeQuery();
//    }
//
//    public ResultSet getMonthEvent() throws SQLException {
//        String query = "{CALL get_month_event()}";
//        CallableStatement stmt = connection.prepareCall(query);
//        return stmt.executeQuery();
//    }
//
//    public void updateEvent(int eventId, String title, String description, Timestamp eventDate, int createdBy, int classroomId, Timestamp createdAt) throws SQLException {
//        String query = "{CALL update_event(?,?,?,?,?,?,?)}";
//        try (CallableStatement stmt = connection.prepareCall(query)) {
//            stmt.setInt(1, eventId);
//            stmt.setString(2, title);
//            stmt.setString(3, description);
//            stmt.setTimestamp(4, eventDate);
//            stmt.setInt(5, createdBy);
//            stmt.setInt(6, classroomId);
//            stmt.setTimestamp(7,createdAt);
//            stmt.execute();
//        }
//    }
//
//    public void deleteEvent(int eventId) throws SQLException{
//        String query = "{CALL delete_event(?)}";
//        try (Connection connection = DB_Connection.Get_Connection();
//             CallableStatement stmt = connection.prepareCall(query)){
//            stmt.setInt(1, eventId);
//            stmt.execute();
//        }
//    }
//
//    public class Main{
//        public static void main(String[] args) {
//            try (Connection connection = DB_Connection.Get_Connection();
//                 CallableStatement callableStatement = connection.prepareCall(sql)){
//                event_ctr.createEvent(1, "Event Title", "Event Description", Timestamp.valueOf("2025-01-06 14:00:00"), 1, 1, Timestamp.valueOf("2025-01-06 13:00:00"));
//            //    ResultSet event = event_ctr.getEvent(1);
//                while (event.next()) {
//                    System.out.println("Event ID: " + event.getInt("event_id"));
//                    System.out.println("Title: " + event.getString("title"));
//                    System.out.println("Description: " + event.getString("description"));
//                    System.out.println("Event Date: " + event.getTimestamp("event_date"));
//                    System.out.println("Created By: " + event.getInt("created_by"));
//                    System.out.println("Classroom ID: " + event.getInt("classroom_id"));
//                    System.out.println("Created At: " + event.getTimestamp("created_at"));
//                }
//            } catch (SQLException e){
//                e.printStackTrace();
//            }
//        }
//    }
//}
