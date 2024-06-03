// InventoryView.java
package view;

import main.Shop;
import model.Product;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class InventoryView extends JFrame {

    private JPanel contentPane;
    private JTable table;

    /**
     * Create the frame.
     */
    public InventoryView(Shop shop) {
        setTitle("Inventario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        scrollPane.setViewportView(table);

        DefaultTableModel model = new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Codigo", "Nombre", "Precio publico", "Precio mayorista", "Stock", "Disponible"
            }
        );
        table.setModel(model);

        loadInventoryData(model, shop);
    }

    private void loadInventoryData(DefaultTableModel model, Shop shop) {
        ArrayList<Product> inventory = shop.getInventory();
        int codigo = 1;

        for (Product product : inventory) {
            double publicPrice = product.getWholesalerPrice() * 2;
            String available = product.getStock() > 0 ? "Disponible" : "No disponible";
            model.addRow(new Object[]{codigo++, product.getName(), publicPrice, product.getWholesalerPrice(), product.getStock(), available});
        }
    }
}
