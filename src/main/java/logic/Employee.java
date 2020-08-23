package logic;

import java.sql.Date;

public class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private String emial;
    private String phoneNumber;
    private Date hireDate;
    private int jobId;
    private float salary;
    private float commissionPct;
    private int managerId;
    private int depertmentId;

    public Employee(int id, final String firstName, final String lastName, final String emial, final String phoneNumber,
                    final Date hireDate, final Integer jobId, final Float salary, final Float commissionPct,
                    final Integer managerId, Integer depertmentId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emial = emial;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.jobId = jobId;
        this.salary = salary;
        this.commissionPct = commissionPct;
        this.managerId = managerId;
        this.depertmentId = depertmentId;
    }

    @Override
    public String toString() {
        return lastName;
    }
}
