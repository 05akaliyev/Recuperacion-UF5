// Sale.java
package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Sale {
    private Client client;
    private ArrayList<Product> products;
    private double amount;
    private Date date;

    public Sale(Client client, ArrayList<Product> products, double amount, Date date) {
        this.client = client;
        this.products = products;
        this.amount = amount;
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public String toFileFormat() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        StringBuilder sb = new StringBuilder();
        sb.append("Client-").append(client.getName()).append("; ");
        sb.append("Date-").append(dateFormat.format(date)).append("; ");
        sb.append("Products-");
        for (Product product : products) {
            sb.append(product.getName()).append(", ").append(product.getPublicPrice()).append("€; ");
        }
        sb.append("Amount-").append(amount).append(";");
        return sb.toString();
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente: ").append(client.getName()).append("\n");
        sb.append("Fecha: ").append(dateFormat.format(date)).append("\n");
        sb.append("Productos:\n");
        for (Product product : products) {
            sb.append("- ").append(product.getName()).append(": ").append(product.getPublicPrice()).append("€\n");
        }
        sb.append("Total: ").append(amount).append("€");
        return sb.toString();
    }
}
