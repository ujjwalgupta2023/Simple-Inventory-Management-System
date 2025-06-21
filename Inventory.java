import java.util.*;
import java.io.*;

public class Inventory {
    private List<Product> productList = new ArrayList<>();
    private final String FILE_NAME = "products.txt";

    public Inventory() {
        loadFromFile();
    }

    public void addProduct(Product p) {
        productList.add(p);
        saveToFile();
        System.out.println("✅ Product added successfully.");
    }

    public void viewProducts() {
        if (productList.isEmpty()) {
            System.out.println("⚠️ Inventory is empty.");
            return;
        }
        System.out.println("📦 Inventory:");
        for (Product p : productList) {
            System.out.println(p);
        }
    }

    public void updateProduct(int id, int quantity, double price) {
        for (Product p : productList) {
            if (p.getId() == id) {
                p.setQuantity(quantity);
                p.setPrice(price);
                saveToFile();
                System.out.println("✅ Product updated.");
                return;
            }
        }
        System.out.println("❌ Product not found.");
    }

    public void deleteProduct(int id) {
        boolean removed = productList.removeIf(p -> p.getId() == id);
        if (removed) {
            saveToFile();
            System.out.println("🗑️ Product deleted.");
        } else {
            System.out.println("❌ Product not found.");
        }
    }

    public void showLowStock(int threshold) {
        boolean found = false;
        for (Product p : productList) {
            if (p.getQuantity() < threshold) {
                System.out.println("⚠️ Low stock: " + p);
                found = true;
            }
        }
        if (!found) System.out.println("✅ No low stock items.");
    }

    private void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Product p : productList) {
                bw.write(p.getId() + "," + p.getName() + "," + p.getQuantity() + "," + p.getPrice());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Error saving to file: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int qty = Integer.parseInt(parts[2]);
                    double price = Double.parseDouble(parts[3]);
                    productList.add(new Product(id, name, qty, price));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("❌ Error loading from file: " + e.getMessage());
        }
    }
}
