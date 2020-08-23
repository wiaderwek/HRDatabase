package logic;

public class Department {

    int id;
    String name;
    int managerId;
    int locationId;

    public Department(int id, final String name, int managerId, int locationId) {
        this.id = id;
        this.name = name;
        this.managerId = managerId;
        this.locationId = locationId;
    }

    @Override
    public String toString() {
        return name;
    }
}
