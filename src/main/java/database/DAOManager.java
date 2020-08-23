package database;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Data Access Object to manage connection to database.
 */
public class DAOManager {

    private ConnectionFactory connectionFactory;
    private Connection connection;

    public static DAOManager getInstance() {
        return DAOManagerSingleton.INSTANCE.get();
    }

    /**
     * open connection to database
     *
     * @throws SQLException
     */
    public void open() throws SQLException {
        try {
            if (this.connection == null || this.connection.isClosed())
                this.connection = connectionFactory.getConnection();
        } catch (SQLException e) {
            throw e;
        }
    }

    /**
     * close connection to database
     *
     * @throws SQLException
     */
    public void close() throws SQLException {
        try {
            if (this.connection != null && !this.connection.isClosed())
                this.connection.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    /**
     * open connection to database if connection was never opened or if it closed.
     * 
     * @throws SQLException
     */
    private void ensureConnection() throws SQLException {
        try {
            if (this.connection == null || this.connection.isClosed()) this.open();
        } catch (SQLException e) {
            throw e;
        }
    }

    private DAOManager() {
        connectionFactory = ConnectionFactory.getInstance();
    }

    private static class DAOManagerSingleton {
        public static final ThreadLocal<DAOManager> INSTANCE;

        static {
            INSTANCE = ThreadLocal.withInitial(DAOManager::new);
        }
    }
}
