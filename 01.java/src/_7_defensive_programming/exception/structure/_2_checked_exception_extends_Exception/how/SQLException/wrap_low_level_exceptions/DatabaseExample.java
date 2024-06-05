package _7_defensive_programming.exception.structure._2_checked_exception_extends_Exception.how.SQLException.wrap_low_level_exceptions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;


public class DatabaseExample {
    void connectDb() throws HighLevelException{
        Connection conn = null;
        Statement stmt = null;
        try {
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/DBNAME", "username", "password");

            // Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "SELECT id, first, last, age FROM Employees";
            stmt.executeQuery(sql);
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new HighLevelException("이런 경위로 에러 발생함", e); //Lower Level Exception을 고수준 Exception에 첨부해서 보냄
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            } // Nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } // End finally try
        } // End try
        System.out.println("Goodbye!");
    } // End main
}
