package Exercises;

import database.DAOManager;
import database.DepartmentDAO;
import database.EmployeeDAO;
import logic.Department;
import logic.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * solution of exercise 2
 */
public class Exercise2 {

    public static void main(String[] args) {
        DAOManager dm = DAOManager.getInstance();
        List<String> lastNames = Arrays.asList("Kowalski", "Nowak");
        float maxSalaryDifference = 25;

        try {
            DepartmentDAO departmentDAO = dm.geDepartmentDAO();
            List<Department> departments = departmentDAO.getDepartmentsByLastNameAndMaxSalaryDifference(lastNames,
                    maxSalaryDifference);
            System.out.println(departments);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
