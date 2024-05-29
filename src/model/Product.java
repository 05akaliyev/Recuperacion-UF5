// Product.java
package model;

public class Product {
    private String name;
    private double wholesalerPrice;
    private boolean isAvailable;
    private int stock;
    private static double TAX_RATE;

    public Product(String name, double wholesalerPrice, boolean isAvailable, int stock, double taxRate) {
        this.name = name;
        this.wholesalerPrice = wholesalerPrice;
        this.isAvailable = isAvailable;
        this.stock = stock;
        TAX_RATE = taxRate;
    }

    public String getName() {
        return name;
    }

    public double getWholesalerPrice() {
        return wholesalerPrice;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getStock() {
        return stock;
    }

    public void addStock(int quantity) {
        this.stock += quantity;
    }

    public void setExpiration(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void reduceStock(int quantity) {
        if (this.stock >= quantity) {
            this.stock -= quantity;
        } else {
            throw new IllegalArgumentException("Not enough stock available");
        }
    }

    public double getPublicPrice() {
        return wholesalerPrice * TAX_RATE;
    }

    @Override
    public String toString() {
        return "Producto: " + name + ", Precio mayorista: " + wholesalerPrice + ", Disponible: " + isAvailable + ", Stock: " + stock;
    }
}
