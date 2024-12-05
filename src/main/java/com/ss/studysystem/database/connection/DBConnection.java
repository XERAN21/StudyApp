package com.ss.studysystem.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.ss.studysystem.database.template.base_commands;

public abstract class DBConnection<T> implements base_commands<T> {

    private static final String URL = "jdbc:mysql://localhost:6789/luminoom_ver1";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    protected Connection connection;

    public DBConnection(){
        try{
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }catch (SQLException e){
            throw new RuntimeException("Failed to connect to database server", e);
        }
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
