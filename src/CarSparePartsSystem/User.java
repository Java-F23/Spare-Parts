package CarSparePartsSystem;

import java.util.*;
import java.util.Scanner;

public class User {
	Scanner scanner = new Scanner(System.in);
    private ShoppingCart shoppingCart;
    private Inventory inventory; // Assuming Inventory class is available
    private Set<SparePart> favoriteParts;

    public User(Inventory inventory) {
        this.shoppingCart = new ShoppingCart();
        this.inventory = inventory;
        this.favoriteParts = new HashSet<>();
    }

    public void userMode() {
        boolean running = true;

        while (running) {
            System.out.println("\nUser Mode - Choose an option:");
            System.out.println("1. Browse Spare Parts");
            System.out.println("2. Search for Spare Parts");
            System.out.println("3. View Detailed Information");
            System.out.println("4. Check Availability and Stock");
            System.out.println("5. Mark/View Favorites");
            System.out.println("6. Check Pricing Information");
            System.out.println("7. Add to Cart and Place Order");
            System.out.println("8. View Warranty Information");
            System.out.println("9. Check Maintenance History");
            System.out.println("10. View Cart");
            System.out.println("11. Checkout");
            System.out.println("12. Exit User Mode");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                	browseSparePartsByCategory();
                    break;
                case 2:
                    searchSpareParts();
                    break;
                case 3:
                    viewDetailedInformation();
                    break;
                case 4:
                    checkAvailabilityAndStock();
                    break;
                case 5:
                    manageFavorites();
                    break;
                case 6:
                    checkPricingInformation();
                    break;
                case 7:
                    addToCartAndOrder();
                    break;
                case 8:
                    viewWarrantyInformation();
                    break;
                case 9:
                    checkMaintenanceHistory();
                    break;
                case 10:
                    viewCart();
                    break;
                case 11:
                    checkout();
                    break;
                case 12:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    
  

    private void browseSparePartsByCategory() {
        System.out.print("Enter category to browse: ");
        String category = scanner.nextLine();
        List<SparePart> parts = inventory.getSparePartsByCategory(category);
        parts.forEach(System.out::println);
    }

    private void searchSpareParts() {
        System.out.print("Enter search criteria (type/compatibility): ");
        String criteria = scanner.next();
        List<SparePart> results = inventory.searchSpareParts(criteria);
        results.forEach(System.out::println);
    }

    private void viewDetailedInformation() {
        System.out.print("Enter part ID for details: ");
        String partId = scanner.next();
        SparePart part = inventory.getSparePartById(partId);
        System.out.println(part.getDetailedInformation());
    }

    private void checkAvailabilityAndStock() {
        System.out.print("Enter part ID to check stock: ");
        String partId = scanner.nextLine();
        SparePart part = inventory.getSparePartById(partId);
        if (part != null) {
            System.out.println("Stock level: " + part.getStockLevel());
        } else {
            System.out.println("Part not found.");
        }
    }

    private void manageFavorites() {
        System.out.println("1. Add to Favorites");
        System.out.println("2. View Favorites");
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.print("Enter part ID to favorite: ");
            String partId = scanner.next();
            SparePart part = inventory.getSparePartById(partId);
            favoriteParts.add(part);
        } else {
            favoriteParts.forEach(System.out::println);
        }
    }

    private void checkPricingInformation() {
        System.out.print("Enter part ID to check price: ");
        String partId = scanner.next();
        SparePart part = inventory.getSparePartById(partId);
        System.out.println("Price: " + part.getPrice());
    }

    private void addToCartAndOrder() {
        System.out.print("Enter part ID to add to cart: ");
        String partId = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume the newline left-over

        SparePart part = inventory.getSparePartById(partId);
        if (part != null) {
            shoppingCart.addItem(part, quantity);
            System.out.println("Added to cart. Current cart:");
            viewCart();
        } else {
            System.out.println("Part not found.");
        }
    }

    private void viewWarrantyInformation() {
        System.out.print("Enter part ID to view warranty: ");
        String partId = scanner.next();
        SparePart part = inventory.getSparePartById(partId);
        System.out.println("Warranty: " + part.getWarrantyInfo());
    }

    private void checkMaintenanceHistory() {
        System.out.print("Enter part ID to check maintenance history: ");
        String partId = scanner.next();
        SparePart part = inventory.getSparePartById(partId);
        System.out.println("Maintenance History: " + part.getMaintenanceHistory());
    }

    private void viewCart() {
        shoppingCart.getItems().forEach(item -> System.out.println(item + ", Quantity: " + shoppingCart.getQuantity(item)));
    }

    private void checkout() {
        System.out.println("Finalizing order...");
        Order order = shoppingCart.checkout();
        System.out.println("Order placed: " + order);
        // Further implementation for order processing
    }

    // Additional methods for user functionalities can be added here
}

    