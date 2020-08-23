package database;

import logic.Department;
import logic.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for DEPARTMENT table
 */
public class DepartmentDAO extends GenericDAO {

    protected DepartmentDAO(Connection connection) {
        super(connection);
    }

    /**
     *
     * @param lastNames list of last names of employees
     * @param maxSalaryDifference difference between max salary in percentage
     * @return list of {@link Department} with given last names and salary lower then max salary on this position by
     * maxSalaryDifference
     */
    public ArrayList<Department> getDepartmentsByLastNameAndMaxSalaryDifference(final List<String> lastNames,
                                                                                float maxSalaryDifference) {
        ArrayList<Department> departments = new ArrayList<>();

        if (!lastNames.isEmpty()) {
            String query = "SELECT DISTINCT d.DEPARTMENT_ID, d.DEPARTMENT_NAME, d.MANAGER_ID, d.LOCATION_ID " +
                    "FROM EMPLOYEES e " +
                    "JOIN JOB_HISTORY jh ON e.EMPLOYEE_ID=jh.EMPLOYEE_ID " +
                    "JOIN JOBS j on jh.JOB_ID=j.JOB_ID " +
                    "JOIN DEPARTMENTS d ON jh.DEPARTMENT_ID=d.DEPARTMENT_ID " +
                    "WHERE e.LAST_NAME IN ( ";

            for (int i = 0; i < lastNames.size() - 1; ++i) {
                query += "?, ";
            }
            query += "?) " +
                     "AND jh.START_DATE = (SELECT MAX(START_DATE) from JOB_HISTORY WHERE EMPLOYEE_ID=e.EMPLOYEE_ID)\n" +
                     "AND (100 - ((e.SALARY/j.MAX_SALARY) * 100)) > ? " +
                     "ORDER BY d.DEPARTMENT_NAME";

            try (PreparedStatement preStmt = connection.prepareStatement(query)) {
                for (int i = 0; i < lastNames.size(); ++i) {
                    preStmt.setString(i + 1, lastNames.get(i));
                }
                preStmt.setFloat(lastNames.size() + 1, maxSalaryDifference);
                try (ResultSet res = preStmt.executeQuery()) {
                    while (res.next()) {
                        departments.add(mapToObject(res));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return departments;
    }

    /**
     *
     * @param res result from SQL query
     * @return database row mapped to {@link Department}
     * @throws SQLException
     */
    @Override
    protected Department mapToObject(ResultSet res) throws SQLException {
        final int id = res.getInt("DEPARTMENT_ID");
        final String name = res.getString("DEPARTMENT_ID");
        final int managerId = res.getInt("MANAGER_ID");
        final int locationId = res.getInt("LOCATION_ID");

        return new Department(id, name, managerId, locationId);
    }
}
