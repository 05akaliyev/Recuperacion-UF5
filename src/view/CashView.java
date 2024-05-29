package view;

import main.Shop;

import javax.swing.*;

public class CashView extends JDialog {
    public CashView(Shop shop) {
        setTitle("Cash View");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel cashLabel = new JLabel("Current Cash:");
        cashLabel.setBounds(10, 10, 100, 25);
        add(cashLabel);

        JTextField cashField = new JTextField(shop.getCash().toString());
        cashField.setBounds(120, 10, 160, 25);
        cashField.setEditable(false);
        add(cashField);

        JButton closeButton = new JButton("Close");
        closeButton.setBounds(100, 50, 80, 25);
        closeButton.addActionListener(e -> dispose());
        add(closeButton);

        setModal(true);
        setVisible(true);
    }
}
