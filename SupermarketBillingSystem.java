package SupermarketBillingSystem;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

public class SupermarketBillingSystem {
    private List<Product> products;
    private Scanner scanner;

    public SupermarketBillingSystem() {
        this.products = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void addProduct() {
        System.out.println("Enter product name:");
        String name = scanner.nextLine();
        System.out.println("Enter product price:");
        double price = scanner.nextDouble();
        scanner.nextLine(); // consume newline left-over
        System.out.println("Enter product quantity:");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // consume newline left-over

        Product product = new Product(name, price, quantity);
        products.add(product);
        System.out.println("Product added successfully!");
    }

    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        System.out.println("Available products:");
        for (Product product : products) {
            System.out.println("Name: " + product.getName() + ", Price: " + product.getPrice() + ", Quantity: " + product.getQuantity());
        }
    }

    public void generateBill() {
        System.out.println("Enter product name to purchase:");
        String name = scanner.nextLine();
        Product product = findProduct(name);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.println("Enter quantity to purchase:");
        int quantityToPurchase = scanner.nextInt();
        scanner.nextLine(); // consume newline left-over

        if (quantityToPurchase > product.getQuantity()) {
            System.out.println("Not enough quantity available.");
            return;
        }

        double total = product.getPrice() * quantityToPurchase;
        System.out.println("Bill generated successfully!");
        System.out.println("Product: " + product.getName());
        System.out.println("Quantity: " + quantityToPurchase);
        System.out.println("Total: " + total);

        product.setQuantity(product.getQuantity() - quantityToPurchase);
    }

    private Product findProduct(String name) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }

    public void run() {
        while (true) {
            System.out.println("1. Add product");
            System.out.println("2. Display products");
            System.out.println("3. Generate bill");
            System.out.println("4. Exit");
            System.out.println("Enter your choice:");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline left-over

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    displayProducts();
                    break;
                case 3:
                    generateBill();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        SupermarketBillingSystem system = new SupermarketBillingSystem();
        system.run();
    }
}