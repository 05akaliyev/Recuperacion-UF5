// Shop.java
package main;

import model.Amount;
import model.Client;
import model.Employee;
import model.Product;
import model.Sale;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Shop {
    private Amount cash = new Amount(100.00, "EUR");
    private ArrayList<Product> inventory;
    private ArrayList<Sale> sales;

    public static final double TAX_RATE = 1.04;

    public Shop() {
        inventory = new ArrayList<>();
        sales = new ArrayList<>();
    }

    public static void main(String[] args) {
        Shop shop = new Shop();

        if (!shop.initSession()) {
            System.out.println("Acceso denegado. Fin del programa.");
            return;
        }

        shop.loadInventoryFromFile("files/inputInventory.txt");

        Scanner scanner = new Scanner(System.in);
        int option;
        boolean exit = false;

        do {
            System.out.println("\n===========================");
            System.out.println("Menu principal miTienda.com");
            System.out.println("===========================");
            System.out.println("1) Contar caja");
            System.out.println("2) Añadir producto");
            System.out.println("3) Añadir stock");
            System.out.println("4) Marcar producto próxima caducidad");
            System.out.println("5) Ver inventario");
            System.out.println("6) Venta");
            System.out.println("7) Ver ventas");
            System.out.println("8) Exportar ventas a fichero");
            System.out.println("9) Eliminar producto");
            System.out.println("10) Salir programa");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    shop.showCash();
                    break;
                case 2:
                    shop.addProduct();
                    break;
                case 3:
                    shop.addStock();
                    break;
                case 4:
                    shop.setExpired();
                    break;
                case 5:
                    shop.showInventory();
                    break;
                case 6:
                    shop.sale();
                    break;
                case 7:
                    shop.showSales();
                    break;
                case 8:
                    shop.exportSalesToFile();
                    break;
                case 9:
                    shop.removeProduct();
                    break;
                case 10:
                    exit = true;
                    break;
            }

        } while (!exit);

        scanner.close();
    }

    private boolean initSession() {
        Scanner scanner = new Scanner(System.in);
        Employee employee = new Employee("test", Employee.USER);

        System.out.print("Número de empleado: ");
        int userId = scanner.nextInt();
        System.out.print("Contraseña: ");
        String password = scanner.next();

        return employee.login(userId, password);
    }

    public void loadInventoryFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("; ");
                String name = parts[0].split(": ")[1];
                double wholesalerPrice = Double.parseDouble(parts[1].split(": ")[1]);
                int stock = Integer.parseInt(parts[2].split(": ")[1]);
                inventory.add(new Product(name, wholesalerPrice, true, stock, TAX_RATE));
            }
            System.out.println("Inventario cargado correctamente desde el archivo.");
        } catch (IOException e) {
            System.out.println("Error al cargar el inventario desde el archivo: " + e.getMessage());
        }
    }

    private void showCash() {
        System.out.println("Dinero actual en caja: " + cash);
    }

    private void addProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nombre: ");
        String name = scanner.nextLine();
        System.out.print("Precio mayorista: ");
        double wholesalerPrice = scanner.nextDouble();
        System.out.print("Stock: ");
        int stock = scanner.nextInt();

        inventory.add(new Product(name, wholesalerPrice, true, stock, TAX_RATE));
        System.out.println("Producto añadido correctamente al inventario.");
    }

    private void addStock() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Seleccione un nombre de producto: ");
        String name = scanner.nextLine();
        Product product = findProduct(name);

        if (product != null) {
            System.out.print("Seleccione la cantidad a añadir: ");
            int quantity = scanner.nextInt();
            product.addStock(quantity);
            System.out.println("Stock añadido correctamente.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private void setExpired() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Seleccione un nombre de producto: ");
        String name = scanner.nextLine();
        Product product = findProduct(name);

        if (product != null) {
            product.setExpiration(false);
            System.out.println("Producto marcado con próxima caducidad.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private void showInventory() {
        System.out.println("Inventario actual:");
        for (Product product : inventory) {
            System.out.println(product);
        }
    }

    private void sale() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Product> products = new ArrayList<>();
        boolean exit = false;
        double totalAmount = 0.0;

        System.out.print("Nombre del cliente: ");
        String clientName = scanner.nextLine();
        Client client = new Client(clientName, Client.MEMBER_ID);

        while (!exit) {
            System.out.print("Seleccione un producto: ");
            String productName = scanner.nextLine();
            Product product = findProduct(productName);

            if (product != null) {
                System.out.print("Cantidad: ");
                int quantity = scanner.nextInt();
                scanner.nextLine(); // consume the leftover newline

                if (quantity <= product.getStock()) {
                    product.reduceStock(quantity);
                    products.add(product);
                    totalAmount += product.getPublicPrice() * quantity;
                } else {
                    System.out.println("Cantidad no disponible en stock.");
                }
            } else {
                System.out.println("Producto no encontrado.");
            }

            System.out.print("¿Agregar otro producto? (si/no): ");
            String response = scanner.nextLine();
            exit = !response.equalsIgnoreCase("si");
        }

        System.out.println("Total a pagar: " + totalAmount + " EUR");

        if (client.pay(new Amount(totalAmount, "EUR"))) {
            sales.add(new Sale(client, products, totalAmount, new Date()));
            System.out.println("Venta realizada con éxito.");
        } else {
            sales.add(new Sale(client, products, totalAmount, new Date()));
            System.out.println("Venta realizada con deuda.");
        }
    }

    private void showSales() {
        System.out.println("Historial de ventas:");
        for (Sale sale : sales) {
            System.out.println(sale);
        }
    }

    private void exportSalesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("files/outputSales.txt"))) {
            for (Sale sale : sales) {
                writer.write(sale.toFileFormat());
                writer.newLine();
            }
            System.out.println("Ventas exportadas correctamente al archivo.");
        } catch (IOException e) {
            System.out.println("Error al exportar las ventas al archivo: " + e.getMessage());
        }
    }

    private void removeProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nombre del producto a eliminar: ");
        String name = scanner.nextLine();
        Product product = findProduct(name);

        if (product != null) {
            inventory.remove(product);
            System.out.println("Producto eliminado correctamente.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private Product findProduct(String name) {
        for (Product product : inventory) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }
}
