package view;

import main.Shop;
import model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductView extends JDialog implements ActionListener {
    private JTextField nameField;
    private JTextField priceField;
    private JTextField stockField;
    private JButton okButton;
    private int option;
    private Shop shop;

    public ProductView(Shop shop, int option) {
        this.shop = shop;
        this.option = option;

        setTitle("Product View");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10, 10, 100, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(120, 10, 160, 25);
        add(nameField);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(10, 40, 100, 25);
        add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(120, 40, 160, 25);
        add(priceField);

        JLabel stockLabel = new JLabel("Stock:");
        stockLabel.setBounds(10, 70, 100, 25);
        add(stockLabel);

        stockField = new JTextField();
        stockField.setBounds(120, 70, 160, 25);
        add(stockField);

        okButton = new JButton("OK");
        okButton.setBounds(100, 110, 80, 25);
        okButton.addActionListener(this);
        add(okButton);

        setModal(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText();
        double price = Double.parseDouble(priceField.getText());
        int stock = Integer.parseInt(stockField.getText());
        Product product = shop.findProduct(name);

        switch (option) {
            case 2:
                if (product == null) {
                    shop.addProduct(new Product(name, price, true, stock, Shop.TAX_RATE));
                    JOptionPane.showMessageDialog(this, "Product added successfully");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Product already exists");
                }
                break;
            case 3:
                if (product != null) {
                    product.addStock(stock);
                    JOptionPane.showMessageDialog(this, "Stock updated successfully");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Product does not exist");
                }
                break;
            case 9:
                if (product != null) {
                    shop.removeProduct(product);
                    JOptionPane.showMessageDialog(this, "Product removed successfully");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Product does not exist");
                }
                break;
        }
    }
}
