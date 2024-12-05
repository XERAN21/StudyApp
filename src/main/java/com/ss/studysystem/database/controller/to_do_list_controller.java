package com.ss.studysystem.database.controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.ss.studysystem.Model.To_Do_List;
import com.ss.studysystem.database.connection.DB_Connection;

public class to_do_list_controller {
	
	public static boolean create_to_do_list(To_Do_List to_do) {
		String sql = "CALL create_to_do(?,?,?,?,?)";
		try(Connection connection = DB_Connection.Get_Connection();
			CallableStatement callableStatement = connection.prepareCall(sql)){
			
			callableStatement.setInt(1,to_do.getUser().getId());
			callableStatement.setString(2, to_do.getContent());
			callableStatement.setString(3, to_do.getFreq().toString());
			callableStatement.setBoolean(4, to_do.getIs_complete());
			callableStatement.setTimestamp(5, Timestamp.valueOf(to_do.getCreated_at()));
			
			int row_affected = callableStatement.executeUpdate();
			return row_affected > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static List<To_Do_List> get_to_do_list() {
		String sql = "CALL get_to_do_list()";
		List<To_Do_List> list = new ArrayList<>();

		try(Connection con = DB_Connection.Get_Connection();
			CallableStatement callableStatement = con.prepareCall(sql)){
			ResultSet resultSet = callableStatement.executeQuery();

		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	public static boolean update_to_do_list(To_Do_List to_do) {
		String sql = "CALL update_to_do_list(?,?,?,?,?)";
		try(Connection con = DB_Connection.Get_Connection();
			CallableStatement callableStatement = con.prepareCall(sql)){
			callableStatement.setInt(1, to_do.getUser().getId());
			callableStatement.setString(2, to_do.getContent());
			callableStatement.setInt(3, to_do.getUser().getId());
			callableStatement.setInt(4, to_do.getUser().getId());
			callableStatement.setInt(5, to_do.getUser().getId());

			int row_affected = callableStatement.executeUpdate();
			return row_affected > 0;
		}catch(SQLException e ){
			e.printStackTrace();
			return false;
		}
	}

	public static boolean delete_to_do_list(int uid){
		String sql = "CALL delete_to_do_list(?)";
		try(Connection con = DB_Connection.Get_Connection();
		CallableStatement callableStatement = con.prepareCall(sql)){
			callableStatement.setInt(1,uid);

			int row_affected = callableStatement.executeUpdate();
			return  row_affected > 0;
		} catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
	}
}
