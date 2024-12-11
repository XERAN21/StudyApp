package com.ss.studysystem.database.connection;
import java.sql.*;

public class DB_Connection {

    public static Connection Get_Connection() throws SQLException{

        String ip = "localhost";
        String port = "3306";
        String db = "luminoom_ver1";

        String url = String.format("jdbc:mysql://%s:%s/%s",ip,port,db);

        String username = "remote";
        String password = "12345678";

        return DriverManager.getConnection(url,username,password);

    }
    public static void main(String[] args) {
        try (Connection conn = Get_Connection()) {
            if (conn != null) {
                System.out.println("Connected to the database!");
            }
            else { System.out.println("Failed to make connection!");
            }
        }
    catch (SQLException e) { System.out.println("SQL Exception: " + e.getMessage());
        }
    }
}
