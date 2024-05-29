package model;

public class Employee {
    public static final String USER = "user";
    private String password;
    private String role;

    public Employee(String password, String role) {
        this.password = password;
        this.role = role;
    }

    public boolean login(int employeeId, String password) {
        return employeeId == 123 && this.password.equals(password);
    }
}
