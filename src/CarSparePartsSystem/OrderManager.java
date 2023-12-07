package CarSparePartsSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.io.*;

public class OrderManager {
    private List<Order> orders;
    private static final String FILE_PATH = "orders.txt";
    private Inventory inventory;

    public OrderManager(Inventory inventory) {
        this.orders = new ArrayList<>();
        this.inventory = inventory;
        loadOrders();
    }

    public void addOrder(Order order) {
        if (getOrderById(order.getId()).isEmpty()) {
            orders.add(order);
            saveOrders();
        }
    }

    public Optional<Order> getOrderById(String orderId) {
        return orders.stream()
            .filter(order -> order.getId().equals(orderId))
            .findFirst();
    }

    public void updateOrderStatus(String orderId, String newStatus) {
        getOrderById(orderId).ifPresent(order -> {
            order.setStatus(newStatus);
            saveOrders();
        });
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }

    private void saveOrders() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Order order : orders) {
                writer.write(order.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving orders: " + e.getMessage());
        }
    }

    private void loadOrders() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return; // File does not exist, no orders to load
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Order order = Order.fromFileString(line, inventory);
                if (order != null && !orderExists(order.getId())) {
                    orders.add(order);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading orders: " + e.getMessage());
        }
    }

    private boolean orderExists(String orderId) {
        return orders.stream().anyMatch(order -> order.getId().equals(orderId));
    }
}
