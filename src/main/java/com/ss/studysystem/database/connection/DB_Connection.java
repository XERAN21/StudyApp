package com.ss.studysystem.database.connection;
import java.sql.*;

public class DB_Connection {
    public static Connection Get_Connection() throws SQLException{

        String ip="172.16.200.26";//main one
        String port = "3306";
        
        String ip1 = "192.168.90.78";
        String port1 = "6789";

        String url = String.format("jdbc:mysql://%s:%s/luminoom_ver1",ip,port);//protocol:subprotocol>://<IP>:<port Number>/<DB name>
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
