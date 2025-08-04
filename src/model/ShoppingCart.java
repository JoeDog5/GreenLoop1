package model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private int customerId;
    private List<CartItem> items = new ArrayList<>();

    public ShoppingCart(int customerId) {
        this.customerId = customerId;
    }

    public void addItem(Product product, int quantity) {
        items.add(new CartItem(product, quantity));
    }

    public void removeItem(Product product) {
        items.removeIf(item -> item.getProduct().equals(product));
    }

    public Order checkout() {
        Order order = new Order(customerId, items);
        items.clear();
        return order;
    }
}
