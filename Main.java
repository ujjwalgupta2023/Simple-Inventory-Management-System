import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Inventory Menu ---");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Show Low Stock Items");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter quantity: ");
                    int qty = sc.nextInt();
                    System.out.print("Enter price: ");
                    double price = sc.nextDouble();
                    inventory.addProduct(new Product(id, name, qty, price));
                    break;

                case 2:
                    inventory.viewProducts();
                    break;

                case 3:
                    System.out.print("Enter product ID to update: ");
                    int uid = sc.nextInt();
                    System.out.print("Enter new quantity: ");
                    int uqty = sc.nextInt();
                    System.out.print("Enter new price: ");
                    double uprice = sc.nextDouble();
                    inventory.updateProduct(uid, uqty, uprice);
                    break;

                case 4:
                    System.out.print("Enter product ID to delete: ");
                    int did = sc.nextInt();
                    inventory.deleteProduct(did);
                    break;

                case 5:
                    System.out.print("Enter low stock threshold: ");
                    int threshold = sc.nextInt();
                    inventory.showLowStock(threshold);
                    break;

                case 0:
                    System.out.println("👋 Exiting...");
                    break;

                default:
                    System.out.println("❌ Invalid choice.");
            }

        } while (choice != 0);

        sc.close();
    }
}
