package com.gadgets;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Creating a customer
        Customer customer1 = new Customer(15, "Praveen", "Kumar", "praveen25@gmail.com", "987456321", "Kalpakkam,chennai");

        // Displaying customer details
        System.out.println("===== Customer Details =====");
        customer1.getCustomerDetails();

        // Creating products
        Product product1 = new Product(201, "Smartphone", "Android phone with 8GB RAM", 500.00, 10);
        Product product2 = new Product(202, "Laptop", "Gaming laptop with RTX 3060", 1200.00, 5);

        // Displaying product details
        System.out.println("\n===== Product Details =====");
        System.out.println(product1.getProductDetails());
        System.out.println(product2.getProductDetails());

        // Creating an inventory
        Inventory inventory1 = new Inventory(301, product1, 10);
        Inventory inventory2 = new Inventory(302, product2, 5);

        // Displaying inventory details
        System.out.println("\n===== Inventory Details =====");
        inventory1.displayInventoryDetails();
        inventory2.displayInventoryDetails();

        // Creating an order
        Order order1 = new Order(401, customer1, new Date(), 1700.00);

        // Creating order details
        OrderDetail orderDetail1 = new OrderDetail(501, order1, product1, 2);
        OrderDetail orderDetail2 = new OrderDetail(502, order1, product2, 1);

        // Displaying order details
        System.out.println("\n===== Order Details =====");
        order1.getOrderDetails();

        System.out.println("\n===== Order Detail Information =====");
        orderDetail1.getOrderDetailInfo();
        orderDetail2.getOrderDetailInfo();

        // Updating order status
        order1.updateOrderStatus("Shipped");
        System.out.println("\nUpdated Order Status:");
        order1.getOrderDetails();

        // Reducing stock after purchase
        inventory1.removeFromInventory(2);
        inventory2.removeFromInventory(1);

        // Display updated inventory
        System.out.println("\n===== Updated Inventory After Purchase =====");
        inventory1.displayInventoryDetails();
        inventory2.displayInventoryDetails();
    }
}
