package Exercises;

import database.DAOManager;
import database.EmployeeDAO;
import logic.Employee;

import java.sql.SQLException;
import java.util.List;

/**
 * Solution of exercise 1
 */
public class Exercise1 {

    public static void main(String[] args) {
        DAOManager dm = DAOManager.getInstance();

        try {
            EmployeeDAO employeeDAO = dm.getEmployeeDAO();
            List<Employee> employees = employeeDAO.getEmployeesFromDRKDepartment();
            System.out.println(employees);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
