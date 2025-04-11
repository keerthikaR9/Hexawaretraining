package com.gadgets;

import com.gadgets.exception.InvalidInputException;
import com.gadgets.exception.OrderProcessingException;

public class OrderDetail {
    private int orderDetailID;
    private Order order; // Reference to Order
    private Product product; // Reference to Product
    private int quantity;
    private double subtotal;

    // Constructor
    public OrderDetail(int orderDetailID, Order order, Product product, int quantity)
            throws InvalidInputException, OrderProcessingException {
        
        if (order == null || product == null) {
            throw new InvalidInputException("❌ Order and Product cannot be null.");
        }

        this.orderDetailID = orderDetailID;
        this.order = order;
        this.product = product;
        setQuantity(quantity); // May throw InvalidInputException
        calculateSubtotal();   // Compute subtotal initially
        order.addOrderDetail(this); // May throw OrderProcessingException
    }


    // Private method to calculate subtotal
    private void calculateSubtotal() {
        this.subtotal = product.getPrice() * this.quantity;
    }

    public int getOrderDetailID() {
        return orderDetailID;
    }

    public int getQuantity() {
        return quantity;
    }

    // Setter for Quantity with custom validation
    public void setQuantity(int quantity) throws InvalidInputException {
        if (quantity > 0) {
            this.quantity = quantity;
            calculateSubtotal(); // Update subtotal
            order.calculateTotalAmount(); // Update order total
        } else {
            throw new InvalidInputException("❌ Invalid quantity. Must be greater than 0.");
        }
    }

    public Product getProduct() {
        return product;
    }

    public double getSubtotal() {
        return this.subtotal;
    }

    // Method to apply discount
    public void applyDiscount(double discountPercentage) throws InvalidInputException {
        if (discountPercentage > 0 && discountPercentage <= 100) {
            double discountAmount = this.subtotal * (discountPercentage / 100);
            this.subtotal -= discountAmount;
            order.calculateTotalAmount(); // Update order total after discount
        } else {
            throw new InvalidInputException("❌ Invalid discount percentage. Must be between 0 and 100.");
        }
    }

    // Method to get order detail info
    public String getOrderDetailInfo() {
        return "===== Order Item =====\n" +
               "Order Detail ID: " + orderDetailID + "\n" +
               "Product: " + product.getProductDetails() + "\n" +
               "Quantity: " + quantity + "\n" +
               "Subtotal: $" + String.format("%.2f", subtotal);
    }
}
