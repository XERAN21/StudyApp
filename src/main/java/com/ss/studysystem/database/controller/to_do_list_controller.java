package com.ss.studysystem.database.controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.ss.studysystem.Model.Users;
import com.ss.studysystem.Model.To_Do_List;
import com.ss.studysystem.Model.Frequency;
import com.ss.studysystem.database.connection.DB_Connection;


public class to_do_list_controller {
	
	public static boolean create_to_do_list(To_Do_List to_do, int[] inserted_id) {
		String sql = "CALL create_to_do_list(?,?,?,?,?)";
		try(Connection connection = DB_Connection.Get_Connection();
			CallableStatement callableStatement = connection.prepareCall(sql)){
			
			callableStatement.setInt(1,to_do.getUser().getId());
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

		try(Connection con = DB_Connection.Get_Connection();
			CallableStatement callableStatement = con.prepareCall(sql)){

			callableStatement.setInt(1, id);
			ResultSet resultSet = callableStatement.executeQuery();

			while(resultSet.next()){
				To_Do_List tdl = new To_Do_List();
				tdl.setContent(resultSet.getString("content"));
				tdl.setFreq(Frequency.valueOf(resultSet.getString("frequency")));
				tdl.setIs_complete(resultSet.getBoolean("is_complete"));
				list.add(tdl);
			}

		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
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

	public static void main(String[] args){
		//Create a new user
		Users user = new Users();
		user.setId(1); // Set the user ID

		//Create a new to-do item
		To_Do_List toDo = new To_Do_List();
		toDo.setUser(user);
		toDo.setContent("Finish homework");
		toDo.setFreq(Frequency.MON);
		toDo.setIs_complete(false);

		//Test create_to_do_list method
		int[] inserted_id = new int[1];
		boolean isCreated = to_do_list_controller.create_to_do_list(toDo, inserted_id);
		System.out.println("To-Do Created: " + isCreated + ", ID: " + inserted_id[0]);

		//Test get_to_do_list method
		List<To_Do_List> toDoList = to_do_list_controller.get_to_do_list(1);
		System.out.println("To-Do List: " + toDoList);

		//Update the to-do item
		toDo.setContent("Finish math homework");
		toDo.setIs_complete(true);

		//Test delete_to_do_list method
		boolean isDeleted = to_do_list_controller.delete_to_do_list(inserted_id[0]);
		System.out.println("To-Do Deleted: " + isDeleted);
	}
}