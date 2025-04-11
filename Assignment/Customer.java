package com.gadgets;

import com.gadgets.exception.InvalidInputException;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int customerID;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private int totalOrders;
    private List<Order> orders;

    public Customer(int customerID, String firstName, String lastName, String email) throws InvalidInputException {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        setEmail(email);
        setPhone(phone);
        setAddress(address);
        this.totalOrders = 0;
        this.orders = new ArrayList<>();
    }

    public Customer(int customerID, String firstName, String lastName, String email, String phone, String address) throws InvalidInputException {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        setEmail(email);
        setPhone(phone);
        setAddress(address);
        this.totalOrders = 0;
        this.orders = new ArrayList<>();
    }

	// Getters
    public int getCustomerID() {
        return customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    // Setters with validation using custom exception
    public void setEmail(String email) throws InvalidInputException {
        if (email != null && email.contains("@") && email.contains(".")) {
            this.email = email;
        } else {
            throw new InvalidInputException("Invalid email format: " + email);
        }
    }

    public void setPhone(String phone) throws InvalidInputException {
        if (phone != null && phone.length() == 10 && phone.matches("\\d+")) {
            this.phone = phone;
        } else {
            throw new InvalidInputException("Phone number must be 10 digits: " + phone);
        }
    }

    public void setAddress(String address) throws InvalidInputException {
        if (address != null && !address.trim().isEmpty()) {
            this.address = address;
        } else {
            throw new InvalidInputException("Address cannot be empty.");
        }
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

    // Update customer info using custom exception
    public void updateCustomerInfo(String newEmail, String newPhone, String newAddress) throws InvalidInputException {
        setEmail(newEmail);
        setPhone(newPhone);
        setAddress(newAddress);
    }

    public void placeOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null.");
        }
        orders.add(order);
        totalOrders++;
    }

    public void displayOrders() {
        System.out.println("\n===== Order History for " + firstName + " " + lastName + " =====");
        if (orders.isEmpty()) {
            System.out.println("No orders placed yet.");
        } else {
            for (Order order : orders) {
                order.getOrderDetails();
            }
        }
    }
}
