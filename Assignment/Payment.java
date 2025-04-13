package entity;

import java.time.LocalDate;

public class Payment {
    private int paymentId;
    private int orderId;
    private String paymentMethod;
    private double amount;
    private LocalDate paymentDate;

    // Default constructor
    public Payment() {}
 
    // New constructor with 3 parameters (for convenience)
    public Payment(int orderId, String paymentMethod, double amount) {
        this.orderId = orderId;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.paymentDate = LocalDate.now(); // Set today's date
    }


    // Parameterized constructor
    public Payment(int orderId, String paymentMethod, double amount, LocalDate paymentDate) {
        this.orderId = orderId;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

   

    // Getters and Setters

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    // toString() method (optional but useful for debugging)
    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", orderId=" + orderId +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
