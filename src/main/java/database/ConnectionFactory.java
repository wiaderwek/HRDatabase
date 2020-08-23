package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class to create connection to database
 */
public class ConnectionFactory {
    private final static String connectionUrl = "jdbc:oracle:thin:@localhost:1521:xe";
    private final static String dbUser = "HR";
    private final static String dbPwd = "oracle";

    private static ConnectionFactory connectionFactory = null;

    private ConnectionFactory() {
    }

    public Connection getConnection() throws SQLException {
        Connection conn;
        conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
        return conn;
    }

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }
        return connectionFactory;
    }
}
