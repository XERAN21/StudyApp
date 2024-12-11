package com.ss.studysystem.database.connection;
import java.sql.*;

public class DB_Connection {
    public static Connection Get_Connection() throws SQLException{

        String url = "jdbc:mysql://192.168.70.142:6789/luminoom_ver1";//protocol:subprotocol>://<IP>:<port Number>/<DB name>
        String username = "remote";
        String password = "";
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
