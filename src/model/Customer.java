package model;

public class Customer extends User {
    private ShoppingCart shoppingCart;

    public Customer() {
        this.role = "Customer";
        this.shoppingCart = new ShoppingCart(this.userId);
    }

    public void viewOrderHistory() {
        System.out.println("Displaying order history...");
    }

    public void submitReview(Review review) {
        System.out.println("Review submitted.");
    }
}
