package entity;

import java.time.LocalDateTime;

public class Order {
    private int orderID;
    private int customerID;
    private LocalDateTime orderDate;
    private double totalAmount;
    private String status;  

    public Order() {}

    public Order(int orderID, int customerID, LocalDateTime orderDate, double totalAmount, String status) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;  
    }

    // Getters and Setters
    public int getOrderID() { return orderID; }
    public void setOrderID(int orderID) { this.orderID = orderID; }

    public int getCustomerID() { return customerID; }
    public void setCustomerID(int customerID) { this.customerID = customerID; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public String getStatus() { return status; }  
    public void setStatus(String status) { this.status = status; }  

    @Override
    public String toString() {
        return "Order{" +
               "orderID=" + orderID +
               ", customerID=" + customerID +
               ", orderDate=" + orderDate +
               ", totalAmount=" + totalAmount +
               ", status='" + status + '\'' +  // Include status in the string output
               '}';
    }
}
