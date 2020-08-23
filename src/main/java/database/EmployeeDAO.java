package database;

import logic.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for EMPLOYEE table
 */
public class EmployeeDAO extends GenericDAO {

    protected EmployeeDAO(Connection connection) {
        super(connection);
    }

    /**
     * @return list of {@link Employee} who work in DRK Department
     */
    public List<Employee> getEmployeesFromDRKDepartment() {
        ArrayList<Employee> employees = new ArrayList<>();

        final String query = "SELECT * FROM EMPLOYEES JOIN DEPARTMENTS ON EMPLOYEES.EMPLOYEE_ID = DEPARTMENTS.DEPARTMENT_ID " +
                "WHERE DEPARTMENTS.DEPARTMENT_NAME = ? ORDER BY FIRST_NAME, LAST_NAME";
        final String departmentName = "DRK";


        try (PreparedStatement preStmt = connection.prepareStatement(query)) {
            preStmt.setString(1, departmentName);
            try (ResultSet res = preStmt.executeQuery()) {
                while (res.next()) {
                    employees.add(mapToObject(res));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    /**
     *
     * @param res result from SQL query
     * @return database row mapped to {@link Employee}
     * @throws SQLException
     */
    @Override
    protected Employee mapToObject(ResultSet res) throws SQLException {
        final int id = res.getInt("EMPLOYEE_ID");
        final String firstName = res.getString("FIRST_NAME");
        final String lastName = res.getString("LAST_NAME");
        final String email = res.getString("EMAIL");
        final String phoneNumber = res.getString("PHONE_NUMBER");
        final Date hireDate = res.getDate("HIRE_DATE");
        final int jobId = res.getInt("JOB_ID");
        final float salary = res.getFloat("SALARY");
        final float commissionPct = res.getFloat("COMMISSION_PCT");
        final int managerInt = res.getInt("MANAGER_ID");
        final int departmentId = res.getInt("DEPARTMENT_ID");

        return new Employee(id, firstName, lastName, email, phoneNumber, hireDate, jobId, salary, commissionPct,
                managerInt, departmentId);
    }
}
