package com.hsbc.bookit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConManager {
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection
                    ("jdbc:mysql://localhost/bookitdb","root","20020413kunal!");
        } catch (SQLException e) {
            System.out.println("Connection Error:" + e.getMessage());
        }
        return null;
    }
}