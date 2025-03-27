package com.gadgets;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Order {
    private int orderID;
    private Customer customer;
    private Date orderDate;
    private double totalAmount;
    private String status; // "Processing", "Shipped", "Cancelled"
    private List<OrderDetail> orderDetails; // List of order details

    // Constructor
    public Order(int orderID, Customer customer, Date orderDate, double totalAmount) {
        this.orderID = orderID;
        this.customer = customer;
        this.orderDate = orderDate;
        setTotalAmount(totalAmount); // Use setter for validation
        setStatus("Processing"); // Default status
        this.orderDetails = new ArrayList<>();
    }

    // Setter for totalAmount with validation
    public void setTotalAmount(double totalAmount) {
        if (totalAmount >= 0) {
            this.totalAmount = totalAmount;
        } else {
            System.out.println("Total amount cannot be negative.");
        }
    }

    // Setter for status with validation
    public void setStatus(String status) {
        if (status.equals("Processing") || status.equals("Shipped") || status.equals("Cancelled")) {
            this.status = status;
        } else {
            System.out.println("Invalid order status. Allowed values: Processing, Shipped, Cancelled.");
        }
    }

    // Method to add an order detail
    public void addOrderDetail(OrderDetail orderDetail) {
        if (orderDetail != null && !orderDetails.contains(orderDetail)) {
            orderDetails.add(orderDetail);
            calculateTotalAmount(); // Update total after adding product
        } else {
            System.out.println("Duplicate or invalid order detail, not added.");
        }
    }

    // Method to calculate total order amount
    public void calculateTotalAmount() {
        double total = 0.0;
        for (OrderDetail detail : orderDetails) {
            total += detail.getSubtotal(); // Use the getter method
        }
        setTotalAmount(total); // Use setter for validation
    }

    // Method to get order details
    public void getOrderDetails() {
        System.out.println("\n===== Order Summary =====");
        System.out.println("Order ID: " + orderID);
        System.out.println("Customer: " + customer.getFirstName() + " " + customer.getLastName());
        System.out.println("Date: " + orderDate);
        System.out.println("Total Amount: $" + totalAmount);
        System.out.println("Status: " + status);
        System.out.println("\n===== Ordered Items =====");

        if (orderDetails.isEmpty()) {
            System.out.println("No items in the order.");
        } else {
            for (OrderDetail detail : orderDetails) {
                detail.getOrderDetailInfo();
            }
        }
    }

    // Method to update order status
    public void updateOrderStatus(String newStatus) {
        setStatus(newStatus); // Use setter to ensure validation
        System.out.println("Order Status Updated: " + this.status);
    }

    // Method to cancel an order and restock inventory
    public void cancelOrder(List<Inventory> inventoryList) {
        if ("Cancelled".equals(this.status)) {
            System.out.println("Order is already cancelled.");
            return;
        }

        // Restock products
        for (OrderDetail detail : orderDetails) {
            for (Inventory inventory : inventoryList) {
                if (inventory.getProduct().getProductID() == detail.getProduct().getProductID()) {
                    inventory.addToInventory(detail.getQuantity()); // Update stock
                    break;
                }
            }
        }

        // Update order status
        setStatus("Cancelled");
        System.out.println("Order ID: " + this.orderID + " has been cancelled. Inventory updated.");
    }

    // Getters
    public int getOrderID() {
        return orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public List<OrderDetail> getOrderDetailsList() {
        return orderDetails;
    }
}



