package CarSparePartsSystem;

import java.util.Scanner;
import java.util.List;


public class Admin {
	private Scanner scanner = new Scanner(System.in);
    private Inventory inventory; // Manages spare parts
    private OrderManager orderManager; // Manages orders

    public Admin(Inventory inventory, OrderManager orderManager) {
        this.inventory = inventory;
        this.orderManager = orderManager;
    }
    
    public void adminMode() {
    	String id, name, type, category, specifications, warrantyInfo, maintenanceHistory;
        int stockLevel;
        double price;
        boolean running = true;
        while (running) {
            System.out.println("\nAdmin Mode - Choose an option:");
            System.out.println("1. Add New Spare Part");
            System.out.println("2. Edit Spare Part");
            System.out.println("3. Manage Inventory");
            System.out.println("4. Categorize Spare Parts");
            System.out.println("5. Track and Manage Orders");
            System.out.println("6. Manage Shipment and Delivery");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:

                     // Prompt the user for input and read values
                     System.out.print("Enter ID: ");
                     id = scanner.nextLine();

                     System.out.print("Enter Name: ");
                     name = scanner.nextLine();

                     System.out.print("Enter Type: ");
                     type = scanner.nextLine();

                     System.out.print("Enter Category: ");
                     category = scanner.nextLine();

                     System.out.print("Enter Specifications: ");
                     specifications = scanner.nextLine();

                     System.out.print("Enter Stock Level: ");
                     stockLevel = scanner.nextInt();
                     scanner.nextLine(); // Consume newline

                     System.out.print("Enter Price: ");
                     price = scanner.nextDouble();
                     scanner.nextLine(); // Consume newline

                     System.out.print("Enter Warranty Information: ");
                     warrantyInfo = scanner.nextLine();

                     System.out.print("Enter Maintenance History: ");
                     maintenanceHistory = scanner.nextLine();
                    addNewSparePart(id, name, type, category, specifications, stockLevel, price, warrantyInfo, maintenanceHistory);
                    break;
                case 2:
                    // Prompt the user for input and read values
                    System.out.print("Enter Part ID: ");
                    id = scanner.nextLine();

                    System.out.print("Enter New Name: ");
                    name = scanner.nextLine();

                    System.out.print("Enter New Type: ");
                    type = scanner.nextLine();

                    System.out.print("Enter New Specifications: ");
                    specifications = scanner.nextLine();
                    editSparePart(id, name, type, specifications);
                    break;
                case 3:
                    manageInventory();
                    break;
                case 4:
                	System.out.print("Enter Part ID: ");
                    id = scanner.nextLine();
                    System.out.print("Enter Category: ");
                    category = scanner.nextLine();
                    categorizeSpareParts(id, category);
                    break;
                case 5:
                    trackAndManageOrders();
                    break;
                case 6:
                    manageShipmentAndDelivery();
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // 1. Add New Spare Parts
    public void addNewSparePart(String id, String name, String type, String category, String specifications, int stockLevel, double price, String warrantyInfo, String maintenanceHistory) {
        SparePart newPart = new SparePart(id, name, type, category, specifications, stockLevel, price, warrantyInfo, maintenanceHistory);
        inventory.addSparePart(newPart);
        System.out.println("New spare part added: " + name);
    }

    // 2. Edit and Update Spare Parts
    public void editSparePart(String partId, String newName, String newType, String newSpecifications) {
        SparePart part = inventory.getSparePartById(partId);
        if (part != null) {
            part.setName(newName);
            part.setType(newType);
            part.setSpecifications(newSpecifications);
            System.out.println("Spare part updated: " + partId);
        } else {
            System.out.println("Spare part not found.");
        }
    }

 // 3. Manage Inventory of Spare Parts
    public void manageInventory() {
        boolean managingInventory = true;
        while (managingInventory) {
            System.out.println("\nInventory Management - Choose an option:");
            System.out.println("1. View Inventory");
            System.out.println("2. Add Spare Part to Inventory");
            System.out.println("3. Remove Spare Part from Inventory");
            System.out.println("4. Return to Admin Mode");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // View Inventory
                    displayInventory();
                    break;
                case 2:
                    // Add Spare Part to Inventory
                    addPartToInventory();
                    break;
                case 3:
                    // Remove Spare Part from Inventory
                    removePartFromInventory();
                    break;
                case 4:
                    managingInventory = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void displayInventory() {
        List<SparePart> parts = inventory.getSpareParts();
        if (parts.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("Current Inventory:");
            for (SparePart part : parts) {
                System.out.println(part); // Assuming SparePart class has a meaningful toString() method
            }
        }
    }

    private void addPartToInventory() {
        System.out.println("Enter details for the new spare part:");
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Type: ");
        String type = scanner.nextLine();
        System.out.print("Category: ");
        String category = scanner.nextLine();
        System.out.print("Specifications: ");
        String specifications = scanner.nextLine();
        System.out.print("Stock Level: ");
        int stockLevel = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Warranty Info: ");
        String warrantyInfo = scanner.nextLine();
        System.out.print("Maintenance History: ");
        String maintenanceHistory = scanner.nextLine();

        SparePart newPart = new SparePart(id, name, type, category, specifications, stockLevel, price, warrantyInfo, maintenanceHistory);
        inventory.addSparePart(newPart);
        System.out.println("Spare part added to inventory: " + name);
    }

    private void removePartFromInventory() {
        System.out.print("Enter the ID of the spare part to remove: ");
        String id = scanner.nextLine();
        boolean removed = inventory.removeSparePart(id);
        if (removed) {
            System.out.println("Spare part removed from inventory.");
        } else {
            System.out.println("Spare part not found in inventory.");
        }
    }


    // 4. Categorize Spare Parts
    public void categorizeSpareParts(String partId, String category) {
        SparePart part = inventory.getSparePartById(partId);
        if (part != null) {
            part.setCategory(category);
            System.out.println("Spare part categorized: " + partId);
        } else {
            System.out.println("Spare part not found.");
        }
    }

 // 5. Track and Manage Orders
    public void trackAndManageOrders() {
        boolean managingOrders = true;
        while (managingOrders) {
            System.out.println("\nOrder Management - Choose an option:");
            System.out.println("1. View All Orders");
            System.out.println("2. Update Order Status");
            System.out.println("3. Return to Admin Mode");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    displayAllOrders();
                    break;
                case 2:
                    updateOrderStatus();
                    break;
                case 3:
                    managingOrders = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void displayAllOrders() {
        List<Order> orders = orderManager.getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            System.out.println("All Orders:");
            for (Order order : orders) {
                System.out.println(order); // Assuming Order class has a meaningful toString() method
            }
        }
    }

    private void updateOrderStatus() {
        System.out.print("Enter the Order ID to update: ");
        String orderId = scanner.nextLine();
        System.out.print("Enter the new status: ");
        String newStatus = scanner.nextLine();

        orderManager.updateOrderStatus(orderId, newStatus);
        System.out.println("Order status updated for Order ID: " + orderId);
    }

 // 6. Manage Shipment and Delivery
    public void manageShipmentAndDelivery() {
        boolean managingShipment = true;
        while (managingShipment) {
            System.out.println("\nShipment and Delivery Management - Choose an option:");
            System.out.println("1. View Shipment Details for an Order");
            System.out.println("2. Update Shipment Details for an Order");
            System.out.println("3. Return to Admin Mode");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewShipmentDetails();
                    break;
                case 2:
                    updateShipmentDetails();
                    break;
                case 3:
                    managingShipment = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void viewShipmentDetails() {
        System.out.print("Enter the Order ID to view shipment details: ");
        String orderId = scanner.nextLine();
        Order order = orderManager.getOrderById(orderId).orElse(null);

        if (order != null) {
            // Assuming Order class has a method to get shipment details
            System.out.println("Shipment Details for Order ID " + orderId + ": " + order.getStatus());
        } else {
            System.out.println("Order not found.");
        }
    }

    private void updateShipmentDetails() {
        System.out.print("Enter the Order ID to update shipment details: ");
        String orderId = scanner.nextLine();
        Order order = orderManager.getOrderById(orderId).orElse(null);

        if (order != null) {
            System.out.print("Enter new shipment details (Order Status): ");
            String newShipmentDetails = scanner.nextLine();
            // Assuming Order class has a method to set shipment details
            order.setShipmentDetails(newShipmentDetails);
            System.out.println("Shipment details updated for Order ID: " + orderId);
        } else {
            System.out.println("Order not found.");
        }
    }

    // Additional methods for admin functionalities can be added here
}
