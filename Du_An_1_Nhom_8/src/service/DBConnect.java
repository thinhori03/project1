/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class DBConnect {
<<<<<<< HEAD
public static final String HOSTNAME = "HOANGICH-2004\\SQLEXPRESS";
    public static final String PORT = "1433";
    public static final String DBNAME = "DU_AN_NHOM_8";
    public static final String USERNAME = "Hoangich";
    public static final String PASSWORD = "Hoangich2004";
=======
public static final String HOSTNAME = "NauTi\\SQLEXPRESS";
    public static final String PORT = "1433";
    public static final String DBNAME = "DU_AN_NHOM_8";
    public static final String USERNAME = "sa";
    public static final String PASSWORD = "123";
>>>>>>> 222908cdc8a0471a3552766c491334dd30d28710

    /**
     * Get connection to MSSQL Server
     *
     * @return Connection
     */
    public static Connection getConnection() {

        // Create a variable for the connection string.
        String connectionUrl = "jdbc:sqlserver://" + HOSTNAME + ":" + PORT + ";"
                + "databaseName=" + DBNAME + ";encrypt=true;trustservercertificate=true;";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(connectionUrl, USERNAME, PASSWORD);
        } // Handle any errors that may have occurred.
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public static void main(String[] args) {
        getConnection();
    }

}
