package dev.hbrown.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection createConnection(){

//        String details = "jdbc:postgresql://104.197.147.254:5432/expensedb?user=postgresuser&password=postgrespass";

        String details = System.getenv("P1_CONN_DETAILS");

        try{
            Connection conn = DriverManager.getConnection(details);
            return conn;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

}
