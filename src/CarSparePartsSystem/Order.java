package CarSparePartsSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class Order {
    private String id;
    private Map<SparePart, Integer> orderedParts;
    private double totalCost;
    private String status;
    private static final String FILE_PATH = "orders.txt";

    // Constructor for creating a new order
    public Order(Map<SparePart, Integer> orderedParts) {
        this.id = UUID.randomUUID().toString();
        this.orderedParts = orderedParts;
        this.totalCost = calculateTotalCost();
        this.status = "Pending"; // Default status
        saveToFile();

    }

    // Parameterized constructor for loading from file
    public Order(String id, Map<SparePart, Integer> orderedParts, String status) {
        this.id = id;
        this.orderedParts = new HashMap<>(orderedParts);
        this.totalCost = calculateTotalCost();
        this.status = status;
    }

    private double calculateTotalCost() {
        return orderedParts.entrySet().stream()
            .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
            .sum();
    }

    public void setShipmentDetails(String newStatus) {
        this.status = newStatus;
        saveToFile();
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(this.toFileString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public String toFileString() {
        String orderedPartsString = orderedParts.entrySet().stream()
            .map(entry -> entry.getKey().getId() + ":" + entry.getValue())
            .collect(Collectors.joining(";"));
        return id + "," + status + "," + totalCost + "," + orderedPartsString;
    }

    public static Order fromFileString(String fileString, Inventory inventory) {
        String[] parts = fileString.split(",");
        if (parts.length < 4) return null;

        String id = parts[0];
        String status = parts[1];
        double totalCost = Double.parseDouble(parts[2]);
        Map<SparePart, Integer> orderedParts = new HashMap<>();
        String[] orderedPartsStrings = parts[3].split(";");
        for (String partString : orderedPartsStrings) {
            String[] partDetails = partString.split(":");
            if (partDetails.length == 2) {
                SparePart part = inventory.getSparePartById(partDetails[0]);
                int quantity = Integer.parseInt(partDetails[1]);
                orderedParts.put(part, quantity);
            }
        }

        return new Order(id, orderedParts, status);
    }
    
    public void viewShipmentDetails() {
        System.out.println("Order ID: " + id);
        System.out.println("Order Status: " + status);
        // Optionally, list spare parts in the order
        System.out.println("Spare Parts in the Order:");
            System.out.println(orderedParts);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(id).append("\n");
        sb.append("Status: ").append(status).append("\n");
        sb.append("Total Cost: ").append(totalCost).append("\n");
        sb.append("Ordered Parts:\n");
        for (Map.Entry<SparePart, Integer> entry : orderedParts.entrySet()) {
            sb.append(" - ").append(entry.getKey().getName())
              .append(" (Quantity: ").append(entry.getValue()).append(")\n");
        }
        return sb.toString();
    }

    // Getters and setters
    public String getId() { return id; }
    public Map<SparePart, Integer> getOrderedParts() { return orderedParts; }
    public double getTotalCost() { return totalCost; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // Additional methods for order management can be added here
}