package CarSparePartsSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Scanner;

public class Order {
    private String id;
    Scanner scanner;
    private Map<SparePart, Integer> orderedParts;
    private double totalCost;
    private String status;

    public Order(Map<SparePart, Integer> orderedParts) {
        this.id = UUID.randomUUID().toString();
        this.orderedParts = orderedParts;
        this.totalCost = calculateTotalCost();
        this.status = "Pending"; // Default status
    }

    
    private double calculateTotalCost() {
        return orderedParts.entrySet().stream()
            .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
            .sum();
    }
    
    public void setShipmentDetails(String status) {
        System.out.println("Enter new order status: ");
        String newOrderStatus = scanner.nextLine();
        this.status = newOrderStatus;
    }
    
    public void viewShipmentDetails() {
        System.out.println("Order ID: " + id);
        System.out.println("Order Status: " + status);
        // Optionally, list spare parts in the order
        System.out.println("Spare Parts in the Order:");
            System.out.println(orderedParts);
    }

    // Getters and setters
    public String getId() { return id; }
    public Map<SparePart, Integer> getOrderedParts() { return orderedParts; }
    public double getTotalCost() { return totalCost; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // Additional methods for order management can be added here
}

