package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionUtil {
    private static String dbURL = "jdbc:derby:db;create=true";

    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;

    public static Connection createConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            //Get a connection
            return DriverManager.getConnection(dbURL);
        } catch (Exception except) {
            except.printStackTrace();
            return null;
        }
    }

    public static void shutdown() {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                DriverManager.getConnection(dbURL + ";shutdown=true");
                conn.close();
            }
        } catch (SQLException sqlExcept) {
        }
    }
}
