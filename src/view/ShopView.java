package view;

import main.Shop;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ShopView extends JFrame implements ActionListener, KeyListener {
    private Shop shop;

    public ShopView() {
        shop = new Shop();
        shop.loadInventoryFromFile("files/inputInventory.txt");

        setTitle("Shop Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton cashButton = new JButton("Count Cash");
        cashButton.setBounds(10, 10, 160, 25);
        cashButton.setActionCommand("1");
        cashButton.addActionListener(this);
        add(cashButton);

        JButton addProductButton = new JButton("Add Product");
        addProductButton.setBounds(10, 40, 160, 25);
        addProductButton.setActionCommand("2");
        addProductButton.addActionListener(this);
        add(addProductButton);

        JButton addStockButton = new JButton("Add Stock");
        addStockButton.setBounds(10, 70, 160, 25);
        addStockButton.setActionCommand("3");
        addStockButton.addActionListener(this);
        add(addStockButton);

        JButton removeProductButton = new JButton("Remove Product");
        removeProductButton.setBounds(10, 100, 160, 25);
        removeProductButton.setActionCommand("9");
        removeProductButton.addActionListener(this);
        add(removeProductButton);

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "1":
                openCashView();
                break;
            case "2":
                openProductView(2);
                break;
            case "3":
                openProductView(3);
                break;
            case "9":
                openProductView(9);
                break;
        }
    }

    private void openCashView() {
        new CashView(shop);
    }

    private void openProductView(int option) {
        new ProductView(shop, option);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case '1':
                openCashView();
                break;
            case '2':
                openProductView(2);
                break;
            case '3':
                openProductView(3);
                break;
            case '9':
                openProductView(9);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
