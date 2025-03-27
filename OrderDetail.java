package com.gadgets;

public class OrderDetail {
    private int orderDetailID;
    private Order order; // Reference to Order
    private Product product; // Reference to Product
    private int quantity;
    private double subtotal;

    // Constructor
    public OrderDetail(int orderDetailID, Order order, Product product, int quantity) {
        this.orderDetailID = orderDetailID;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.subtotal = calculateSubtotal(); // Compute subtotal initially
        order.addOrderDetail(this); // Automatically add to order
    }

    // Method to calculate subtotal
    private double calculateSubtotal() {
        return product.getPrice() * quantity;
    }

    // Method to get order detail info
    public void getOrderDetailInfo() {
        System.out.println("\n===== Order Item =====");
        System.out.println("Order Detail ID: " + orderDetailID);
        System.out.println("Product: " + product.getProductDetails());
        System.out.println("Quantity: " + quantity);
        System.out.println("Subtotal: $" + subtotal);
    }

    // Method to update quantity and recalculate subtotal
    public void updateQuantity(int newQuantity) {
        if (newQuantity > 0) {
            this.quantity = newQuantity;
            this.subtotal = calculateSubtotal();
            order.calculateTotalAmount(); // Update order total
        } else {
            System.out.println("Invalid quantity update.");
        }
    }

    // Method to apply discount
    public void addDiscount(double discountPercentage) {
        if (discountPercentage > 0 && discountPercentage <= 100) {
            this.subtotal -= this.subtotal * (discountPercentage / 100);
            order.calculateTotalAmount(); // Update order total after discount
        } else {
            System.out.println("Invalid discount percentage.");
        }
    }

    // Getters
    public int getOrderDetailID() {
        return orderDetailID;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public double getSubtotal() {
        return this.subtotal;
    }
}

