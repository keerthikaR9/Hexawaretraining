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
        setEmail(email);
        setPhone(phone);
        this.address = address;
        this.totalOrders = 0;
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

    // Setters with validation
    public void setEmail(String email) {
        if (email.contains("@")) {
            this.email = email;
        } else {
            System.out.println("Invalid email format.");
        }
    }

    public void setPhone(String phone) {
        if (phone.length() == 10 && phone.matches("\\d+")) {
            this.phone = phone;
        } else {
            System.out.println("Phone number must be 10 digits.");
        }
    }

    public void setAddress(String address) {
        if (!address.isEmpty()) {
            this.address = address;
        } else {
            System.out.println("Address cannot be empty.");
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

    // Method to update customer info
    public void updateCustomerInfo(String newEmail, String newPhone, String newAddress) {
        setEmail(newEmail);
        setPhone(newPhone);
        setAddress(newAddress);
    }

    // Increment total orders
    public void placeOrder() {
        totalOrders++;
    }
}


