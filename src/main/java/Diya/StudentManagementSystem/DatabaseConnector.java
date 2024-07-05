package Diya.StudentManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The DatabaseConnector class provides a method to establish a connection to the
 * StudentManagementSystem database using JDBC.
 * <p>
 * This class contains constants for the database URL, user, and password, and
 * provides a method to get a connection to the database.
 * </p>
 *
 */
public class DatabaseConnector {

    private static final String URL = "jdbc:mysql://localhost:3306/StudentManagementSystem";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    /**
     * Establishes a connection to the StudentManagementSystem database.
     *
     * @return a Connection object that represents a connection to the database
     * @throws SQLException if a database access error occurs or the url is null
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
