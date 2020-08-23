package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Class to be extended by other DAO classes
 */
public abstract class GenericDAO {

    protected Connection connection;

    protected abstract Object mapToObject(ResultSet res) throws SQLException;

    protected Collection mapToObjects(final ResultSet res) throws SQLException {
        Collection result = new ArrayList();
        while (res.next()) {
            result.add(mapToObjects(res));
        }

        return result;
    }

    protected GenericDAO(Connection connection) {
        this.connection = connection;
    }


}
