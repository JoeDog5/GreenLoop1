package model;

import java.util.Date;
import java.util.List;

public class Order {
    private static int nextId = 1;

    private int orderId;
    private int customerId;
    private List<CartItem> orderItems;
    private Date date;
    private String invoicePdf;
    private Status status;

    public enum Status {
        PENDING, SHIPPED, DELIVERED
    }

    public Order(int customerId, List<CartItem> items) {
        this.orderId = nextId++;
        this.customerId = customerId;
        this.orderItems = items;
        this.date = new Date();
        this.status = Status.PENDING;
    }

    public void generateInvoice() {
        this.invoicePdf = "invoice_" + orderId + ".pdf";
    }

    public double getTotal() {
        return orderItems.stream().mapToDouble(i -> i.getProduct().getPrice() * i.getQuantity()).sum();
    }
}
