package com.ss.studysystem.database.controller;

import com.ss.studysystem.Model.Frequency;
import com.ss.studysystem.Model.To_Do_List;
import com.ss.studysystem.database.connection.DB_Connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class to_do_list_controller {

    public static boolean create_to_do_list(To_Do_List to_do, int[] inserted_id) {
        String sql = "CALL create_to_do_list(?,?,?,?,?)";
        try (Connection connection = DB_Connection.Get_Connection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {

            callableStatement.setInt(1, to_do.getUser().getId());
            callableStatement.setString(2, to_do.getContent());
            callableStatement.setString(3, to_do.getFreq().toString());
            callableStatement.setBoolean(4, to_do.getIs_complete());

            int row_affected = callableStatement.executeUpdate();

            if (row_affected > 0) {
                inserted_id[0] = callableStatement.getInt(5);
            }

            return row_affected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<To_Do_List> get_to_do_list(int id) {
        String sql = "CALL get_to_do_list(?)";
        List<To_Do_List> list = new ArrayList<>();

        try (Connection con = DB_Connection.Get_Connection();
             CallableStatement callableStatement = con.prepareCall(sql)) {

            callableStatement.setInt(1, id);
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                To_Do_List tdl = new To_Do_List();
                tdl.setTo_do_list(resultSet.getInt("to_do_id"));
                tdl.setContent(resultSet.getString("content"));
                tdl.setFreq(Frequency.valueOf(resultSet.getString("frequency")));
                tdl.setIs_complete(resultSet.getBoolean("is_complete"));
                list.add(tdl);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public static boolean delete_to_do_list(int uid) {
        String sql = "CALL delete_to_do_list(?)";
        try (Connection con = DB_Connection.Get_Connection();
             CallableStatement callableStatement = con.prepareCall(sql)) {
            callableStatement.setInt(1, uid);

            int row_affected = callableStatement.executeUpdate();
            return row_affected > 0;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static boolean update_task_completion(int id, boolean complete) {
        String sql = "UPDATE to_do_list SET is_complete = ? WHERE to_do_id = ?;";
        try (Connection con = DB_Connection.Get_Connection();
             PreparedStatement psmt = con.prepareStatement(sql)) {

            psmt.setBoolean(1, complete);
            psmt.setInt(2, id);

            int row_affected = psmt.executeUpdate();
            return row_affected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }
    }
}