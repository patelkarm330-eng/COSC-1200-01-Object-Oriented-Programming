/*
 Name: Karm Patel
 Program name: Main
 Date: 28 March 2026
 Description: Main program providing menu product management.
 */


import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

    public class Main {

        // Stores all Product and PerishableProduct objects
        private static final ArrayList<Product> inventory = new ArrayList<>();

        // Scanner for user input
        private static final Scanner scanner = new Scanner(System.in);

        public static void main(String[] args) {

            int choice;

            // Main program loop
            do {
                printMenu();
                choice = readInt("Enter your choice: ");

                switch (choice) {
                    case 1 -> createProduct();
                    case 2 -> createPerishableProduct();
                    case 3 -> editProduct();
                    case 4 -> deleteProduct();
                    case 5 -> displayProduct();
                    case 6 -> displayAll();
                    case 7 -> System.out.println("Exiting program...");
                    default -> System.out.println("Invalid choice.");
                }

            } while (choice != 7);
        }

        // Prints the main menu options
        private static void printMenu() {
            System.out.println("\n=== Product Management Menu ===");
            System.out.println("1) Create Product");
            System.out.println("2) Create Perishable Product");
            System.out.println("3) Edit Product by SKU");
            System.out.println("4) Delete Product by SKU");
            System.out.println("5) Display Product by SKU");
            System.out.println("6) Display all Products");
            System.out.println("7) Exit");
        }

        // Creates a new non‑perishable product
        private static void createProduct() {
            System.out.println("\n-- Create Product --");

            String sku = readSku();
            String name = readNonBlank("Enter product name: ");
            double unitCost = readDouble("Enter unit cost: ");
            double salePrice = readDouble("Enter sale price: ");
            int qtyOnHand = readInt("Enter quantity on hand: ");
            int qtyNeeded = readInt("Enter quantity needed: ");
            System.out.print("Enter special instructions: ");
            String instructions = scanner.nextLine();

            Product p = new Product(sku, name, unitCost, salePrice, qtyOnHand, qtyNeeded, instructions);
            inventory.add(p);

            System.out.println("Product added successfully.");
        }


        // Creates a new perishable product with expiry date
        private static void createPerishableProduct() {
            System.out.println("\n-- Create Perishable Product --");

            String sku = readSku();
            String name = readNonBlank("Enter product name: ");
            double unitCost = readDouble("Enter unit cost: ");
            double salePrice = readDouble("Enter sale price: ");
            int qtyOnHand = readInt("Enter quantity on hand: ");
            int qtyNeeded = readInt("Enter quantity needed: ");
            System.out.print("Enter special instructions: ");
            String instructions = scanner.nextLine();

            System.out.print("Enter expiry date (YYYY-MM-DD): ");
            LocalDate expiry = LocalDate.parse(scanner.nextLine());

            PerishableProduct p = new PerishableProduct(
                    sku, name, unitCost, salePrice, qtyOnHand, qtyNeeded, instructions, expiry);

            inventory.add(p);
            System.out.println("Perishable product added successfully.");
        }

        // Edits an existing product using its SKU
        private static void editProduct() {
            System.out.println("\n-- Edit Product --");
            String sku = readNonBlank("Enter SKU to edit: ");

            Product p = findBySku(sku);
            if (p == null) {
                System.out.println("No product found.");
                return;
            }

            System.out.println("Editing product:");
            System.out.println(p);

            p.setName(readNonBlank("Enter new name: "));
            p.setUnitCost(readDouble("Enter new unit cost: "));
            p.setSalePrice(readDouble("Enter new sale price: "));
            p.setQuantityOnHand(readInt("Enter new quantity on hand: "));
            p.setQuantityNeeded(readInt("Enter new quantity needed: "));
            System.out.print("Enter new special instructions: ");
            p.setSpecialInstructions(scanner.nextLine());

            // Update expiry date if perishable
            if (p instanceof PerishableProduct perishable) {
                System.out.print("Enter new expiry date (YYYY-MM-DD): ");
                perishable.setExpiryDate(LocalDate.parse(scanner.nextLine()));
            }

            System.out.println("Product updated.");
        }

        // Deletes a product from inventory using its SKU
        private static void deleteProduct() {
            System.out.println("\n-- Delete Product --");
            String sku = readNonBlank("Enter SKU to delete: ");

            Product p = findBySku(sku);
            if (p == null) {
                System.out.println("No product found.");
                return;
            }

            inventory.remove(p);
            System.out.println("Product deleted.");
        }

        // Displays a single product by SKU
        private static void displayProduct() {
            System.out.println("\n-- Display Product --");
            String sku = readNonBlank("Enter SKU: ");

            Product p = findBySku(sku);
            if (p == null) {
                System.out.println("No product found.");
            } else {
                System.out.println(p);
            }
        }


        // Displays all products in the inventory
        private static void displayAll() {
            System.out.println("\n-- All Products --");

            if (inventory.isEmpty()) {
                System.out.println("No products in inventory.");
                return;
            }

            for (Product p : inventory) {
                System.out.println("-------------------------");
                System.out.println(p);
            }
        }

        // Searches inventory for a product with the given SKU
        private static Product findBySku(String sku) {
            for (Product p : inventory) {
                if (p.getSku().equals(sku)) {
                    return p;
                }
            }
            return null;
        }


        // Reads and validates SKU input (must be 8+ digits)
        private static String readSku() {
            while (true) {
                System.out.print("Enter SKU (8+ digits): ");
                String sku = scanner.nextLine();
                if (sku.matches("\\d{8,}")) return sku;
                System.out.println("Invalid SKU.");
            }
        }

        // Ensures user enters a non‑blank string
        private static String readNonBlank(String prompt) {
            while (true) {
                System.out.print(prompt);
                String input = scanner.nextLine();
                if (!input.isBlank()) return input;
                System.out.println("Input cannot be blank.");
            }
        }

        // Reads and validates an integer
        private static int readInt(String prompt) {
            while (true) {
                try {
                    System.out.print(prompt);
                    return Integer.parseInt(scanner.nextLine());
                } catch (Exception e) {
                    System.out.println("Invalid number.");
                }
            }
        }

        // Reads and validates a double
        private static double readDouble(String prompt) {
            while (true) {
                try {
                    System.out.print(prompt);
                    return Double.parseDouble(scanner.nextLine());
                } catch (Exception e) {
                    System.out.println("Invalid number.");
                }
            }
        }
    }


