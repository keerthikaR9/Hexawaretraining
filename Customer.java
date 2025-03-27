package com.gadgets;

public class Customer {
    private int customerID;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private int totalOrders;

    // Constructor
    public Customer(int customerID, String firstName, String lastName, String email, String phone, String address) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.totalOrders = 0;
    }
    // Calculate total orders
    public int calculateTotalOrders() {
        return totalOrders;
    }

    // Method to display customer details
    public void getCustomerDetails() {
        System.out.println("Customer ID: " + customerID);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Address: " + address);
        System.out.println("Total Orders: " + totalOrders);
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    // Method to update customer info
    public void updateCustomerInfo(String newEmail, String newPhone, String newAddress) {
        this.email = newEmail;
        this.phone = newPhone;
        this.address = newAddress;
    }
    public void placeOrder() {
        totalOrders++;
    }
}

