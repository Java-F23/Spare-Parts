package CarSparePartsSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderManager {
    private List<Order> orders;

    public OrderManager() {
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public Optional<Order> getOrderById(String orderId) {
        return orders.stream()
            .filter(order -> order.getId().equals(orderId))
            .findFirst();
    }

    public void updateOrderStatus(String orderId, String newStatus) {
        getOrderById(orderId)
            .ifPresent(order -> order.setStatus(newStatus));
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }

    // Additional methods for managing orders can be added here
}
