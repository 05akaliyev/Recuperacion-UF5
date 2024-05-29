package view;

import main.Shop;
import model.Employee;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame implements ActionListener {
    private JTextField employeeIdField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private int loginAttempts = 0;

    public LoginView() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel employeeIdLabel = new JLabel("Employee ID:");
        employeeIdLabel.setBounds(10, 10, 80, 25);
        add(employeeIdLabel);

        employeeIdField = new JTextField();
        employeeIdField.setBounds(100, 10, 160, 25);
        add(employeeIdField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 40, 80, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 40, 160, 25);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(100, 80, 160, 25);
        loginButton.addActionListener(this);
        add(loginButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int employeeId = Integer.parseInt(employeeIdField.getText());
        String password = new String(passwordField.getPassword());
        Employee employee = new Employee("test", Employee.USER);

        if (employee.login(employeeId, password)) {
            JOptionPane.showMessageDialog(this, "Login successful");
            new ShopView();
            dispose();
        } else {
            loginAttempts++;
            if (loginAttempts >= 3) {
                JOptionPane.showMessageDialog(this, "Maximum login attempts reached");
                System.exit(0);
            } else {
                JOptionPane.showMessageDialog(this, "Login failed");
            }
        }
    }
}
