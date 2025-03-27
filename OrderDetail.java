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
        setQuantity(quantity); // Use setter for validation
        calculateSubtotal(); // Compute subtotal initially
        order.addOrderDetail(this); // Automatically add to order
    }

    // Private method to calculate subtotal
    private void calculateSubtotal() {
        this.subtotal = product.getPrice() * this.quantity;
    }

    // Getter for OrderDetail ID
    public int getOrderDetailID() {
        return orderDetailID;
    }

    // Getter for Quantity
    public int getQuantity() {
        return quantity;
    }

    // Setter for Quantity with validation
    public void setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
            calculateSubtotal(); // Update subtotal
            order.calculateTotalAmount(); // Update order total
        } else {
            System.out.println("Invalid quantity. Must be greater than 0.");
        }
    }

    // Getter for Product
    public Product getProduct() {
        return product;
    }

    // Getter for Subtotal
    public double getSubtotal() {
        return this.subtotal;
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

    // Method to get order detail info
    public void getOrderDetailInfo() {
        System.out.println("\n===== Order Item =====");
        System.out.println("Order Detail ID: " + orderDetailID);
        System.out.println("Product: " + product.getProductDetails());
        System.out.println("Quantity: " + quantity);
        System.out.println("Subtotal: $" + subtotal);
    }
}


