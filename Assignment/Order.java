package com.gadgets;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gadgets.exception.InvalidInputException;
import com.gadgets.exception.OrderProcessingException;

public class Order {
    private int orderID;
    private Customer customer;
    private Date orderDate;
    private double totalAmount;
    private String status;
    private List<OrderDetail> orderDetails;

    public Order(int orderID, Customer customer, Date orderDate, double totalAmount) throws InvalidInputException {
        this.orderID = orderID;
        this.customer = customer;
        this.orderDate = orderDate;
        setTotalAmount(totalAmount);
        setStatus("Processing");
        this.orderDetails = new ArrayList<>();
    }

    public void setTotalAmount(double totalAmount) throws InvalidInputException {
        if (totalAmount >= 0) {
            this.totalAmount = totalAmount;
        } else {
            throw new InvalidInputException("❌ Total amount cannot be negative.");
        }
    }

    public void setStatus(String status) throws InvalidInputException {
        if (status.equals("Processing") || status.equals("Shipped") || status.equals("Cancelled")) {
            this.status = status;
        } else {
            throw new InvalidInputException("❌ Invalid order status. Allowed values: Processing, Shipped, Cancelled.");
        }
    }

    public void addOrderDetail(OrderDetail orderDetail) throws OrderProcessingException, InvalidInputException {
        if (orderDetail == null) {
            throw new OrderProcessingException("❌ Cannot add a null order detail.");
        }

        if (orderDetails.contains(orderDetail)) {
            throw new OrderProcessingException("❌ Duplicate order detail detected.");
        }

        orderDetails.add(orderDetail);
        calculateTotalAmount();
    }

    public void calculateTotalAmount() throws InvalidInputException {
        double total = 0.0;
        for (OrderDetail detail : orderDetails) {
            total += detail.getSubtotal();
        }
        setTotalAmount(total);
    }

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

    public void updateOrderStatus(String newStatus) throws InvalidInputException {
        setStatus(newStatus);
        System.out.println("Order Status Updated: " + this.status);
    }

    public void cancelOrder(List<Inventory> inventoryList) throws InvalidInputException {
        if ("Cancelled".equals(this.status)) {
            System.out.println("Order is already cancelled.");
            return;
        }

        for (OrderDetail detail : orderDetails) {
            for (Inventory inventory : inventoryList) {
                if (inventory.getProduct().getProductID() == detail.getProduct().getProductID()) {
                    inventory.addToInventory(detail.getQuantity());
                    break;
                }
            }
        }

        setStatus("Cancelled"); // ✅ Now this compiles
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
