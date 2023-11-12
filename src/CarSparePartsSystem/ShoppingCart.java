package CarSparePartsSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
    private Map<SparePart, Integer> items;

    public ShoppingCart() {
        this.items = new HashMap<>();
    }

    public void addItem(SparePart part, int quantity) {
        items.put(part, items.getOrDefault(part, 0) + quantity);
    }

    public void removeItem(SparePart part) {
        items.remove(part);
    }

    public List<SparePart> getItems() {
        return new ArrayList<>(items.keySet());
    }

    public int getQuantity(SparePart part) {
        return items.getOrDefault(part, 0);
    }

    public Order checkout() {
        Order newOrder = new Order(new HashMap<>(items));
        items.clear();
        return newOrder;
    }

    // Additional methods for shopping cart management can be added here
}
