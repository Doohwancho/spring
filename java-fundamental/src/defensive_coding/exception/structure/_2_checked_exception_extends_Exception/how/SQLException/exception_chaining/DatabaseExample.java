package defensive_coding.exception.structure._2_checked_exception_extends_Exception.how.SQLException.exception_chaining;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;

/*
    Q. rdb 벤더사마다 SQLException 종류/이름이 다 다른데, 이걸
    1. 벤더사 마다 분리하고
    2. 벤더사에 종속된 SQLException 종류들을 documentation 보고 의도 다 파악해서, catch로 다 분기처리한다?
    3. 근데 힘들게 했는데, db 버전 업 하면, 다른 부분 다 체크하고 수동 업데이트한다?

    //TODO - problem: 이거 자동화해주는 라이브러리 없나?
 */

public class DatabaseExample {
    public static void main(String[] args) {
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
        } catch (SQLSyntaxErrorException se) { //TODO - Q. rdb 벤더사마다 다른 SQLException 종류, 메시지 다른데, 어떻게 처리하지?
            // Handle SQL syntax errors
            System.out.println("There was a syntax error in your SQL query.");
            se.printStackTrace();
        } catch (SQLIntegrityConstraintViolationException e) {
            // handle specific SQL exceptions
            System.out.println("Integrity constraint violation: " + e.getMessage());
            // log exception, don't print stack trace in production
        } catch (SQLDataException se) {
            // Handle SQL data errors
            System.out.println("There was a data error.");
            se.printStackTrace();
        } catch (SQLException se) {
            // Handle general SQL errors
            System.out.println("A database error occurred.");
            se.printStackTrace();
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
