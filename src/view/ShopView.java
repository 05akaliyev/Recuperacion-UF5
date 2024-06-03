// ShopView
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import main.Shop;


public class ShopView extends JFrame implements ActionListener, KeyListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton countCageButton;
    private JButton addNewProductButton;
    private JButton addStockButton;
    private JButton deleteProductButton;
    private JButton viewInventoryButton;
    private Shop shop = new Shop();
    private int option;

    /**
     * Create the frame.
     */
    public ShopView() {
        shop.readFileInventory();
        setTitle("Shop view");
        addKeyListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel textLabel = new JLabel("Select one option of the shop:");
        textLabel.setBounds(20, 10, 260, 16);
        contentPane.add(textLabel);

        countCageButton = new JButton("1. Count cage");
        countCageButton.setBounds(30, 40, 250, 50);
        contentPane.add(countCageButton);
        countCageButton.addActionListener(this);
        countCageButton.addKeyListener(this);
        countCageButton.setFocusable(false);

        addNewProductButton = new JButton("2. Add new product");
        addNewProductButton.setBounds(30, 100, 250, 50);
        contentPane.add(addNewProductButton);
        addNewProductButton.addActionListener(this);
        addNewProductButton.addKeyListener(this);
        addNewProductButton.setFocusable(false);

        addStockButton = new JButton("3. Add stock");
        addStockButton.setBounds(30, 160, 250, 50);
        contentPane.add(addStockButton);
        addStockButton.addActionListener(this);
        addStockButton.addKeyListener(this);
        addStockButton.setFocusable(false);

        viewInventoryButton = new JButton("5. Ver inventario"); // New buton
        viewInventoryButton.setBounds(30, 220, 250, 50); // Set appropriate bounds
        contentPane.add(viewInventoryButton);
        viewInventoryButton.addActionListener(this);
        viewInventoryButton.addKeyListener(this);
        viewInventoryButton.setFocusable(false);

        deleteProductButton = new JButton("9. Delete product");
        deleteProductButton.setBounds(30, 280, 250, 50);
        contentPane.add(deleteProductButton);
        deleteProductButton.addActionListener(this);
        deleteProductButton.addKeyListener(this);
        deleteProductButton.setFocusable(false);
    }

    // Getters and Setters
    public JButton getCountCageButton() {
        return countCageButton;
    }

    public void setCountCageButton(JButton countCageButton) {
        this.countCageButton = countCageButton;
    }

    public JButton getAddNewProductButton() {
        return addNewProductButton;
    }

    public void setAddNewProductButton(JButton addNewProductButton) {
        this.addNewProductButton = addNewProductButton;
    }

    public JButton getAddStockButton() {
        return addStockButton;
    }

    public void setAddStockButton(JButton addStockButton) {
        this.addStockButton = addStockButton;
    }

    public JButton getDeleteProductButton() {
        return deleteProductButton;
    }

    public void setDeleteProductButton(JButton deleteProductButton) {
        this.deleteProductButton = deleteProductButton;
    }

    public JButton getViewInventoryButton() {
        return viewInventoryButton;
    }

    public void setViewInventoryButton(JButton viewInventoryButton) {
        this.viewInventoryButton = viewInventoryButton;
    }

    // KeyActions
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent ex) {
        int keyCode = ex.getKeyCode();
        System.out.println("Tecla presionada: " + keyCode);
        ProductView addproductView = new ProductView();
        if (keyCode == 49) {
            option = 1;
        } else if (keyCode == 50) {
            option = 2;
        } else if (keyCode == 51) {
            option = 3;
        } else if (keyCode == 53) {
            option = 5;
        } else if (keyCode == 57) {
            option = 9;
        }
        switch (keyCode) {
            case KeyEvent.VK_1:
                openCashView(option, shop);
                break;
            case KeyEvent.VK_2:
                addproductView.openProductView(option, shop);
                break;
            case KeyEvent.VK_3:
                addproductView.openProductView(option, shop);
                break;
            case KeyEvent.VK_5: 
                openInventoryView(option, shop);
                break;
            case KeyEvent.VK_9:
                addproductView.openProductView(option, shop);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        System.out.println("Tecla liberada: " + keyCode);
    }

    public void actionPerformed(ActionEvent e) {
        ProductView addproductView = new ProductView();
        if (countCageButton == e.getSource()) {
            option = 1;
            openCashView(option, shop);
        } else if (addNewProductButton == e.getSource()) {
            option = 2;
            addproductView.openProductView(option, shop);
        } else if (addStockButton == e.getSource()) {
            option = 3;
            addproductView.openProductView(option, shop);
        } else if (viewInventoryButton == e.getSource()) {
            option = 5;
            openInventoryView(option, shop);
        } else if (deleteProductButton == e.getSource()) {
            option = 9;
            addproductView.openProductView(option, shop);
        }
        // blockButtons();
    }

    public void openCashView(int option, Shop shop) {
        if (option == 1) {
            CashView cashView = new CashView(shop, this);
            cashView.setVisible(true);
        }
    }

    public void openInventoryView(int option, Shop shop) {
        if (option == 5) {
            InventoryView inventoryView = new InventoryView(shop);
            inventoryView.setVisible(true);
        }
    }

    public void blockButtons() {
        countCageButton.setEnabled(false);
        addNewProductButton.setEnabled(false);
        addStockButton.setEnabled(false);
        deleteProductButton.setEnabled(false);
        viewInventoryButton.setEnabled(false);
    }

    public void unlockButtons() {
        countCageButton.setEnabled(true);
        addNewProductButton.setEnabled(true);
        addStockButton.setEnabled(true);
        deleteProductButton.setEnabled(true);
        viewInventoryButton.setEnabled(true);
    }
}
