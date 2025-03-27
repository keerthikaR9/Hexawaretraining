package com.gadgets;
import java.util.Date;
import java.util.List;

public class Inventory {
    private int inventoryID;
    private Product product; // Composition
    private int quantityInStock;
    private Date lastStockUpdate;

    // Constructor
    public Inventory(int inventoryID, Product product, int quantityInStock) {
        this.inventoryID = inventoryID;
        this.product = product;
        this.quantityInStock = quantityInStock;
        this.lastStockUpdate = new Date(); // Set current date
    }

    // Get product details
    public Product getProduct() {
        return product;
    }

    // Get quantity in stock
    public int getQuantityInStock() {
        return quantityInStock;
    }

    // Add stock to inventory
    public void addToInventory(int quantity) {
        this.quantityInStock += quantity;
        this.lastStockUpdate = new Date();
    }

    // Remove stock from inventory
    public void removeFromInventory(int quantity) {
        if (quantity <= quantityInStock) {
            this.quantityInStock -= quantity;
            this.lastStockUpdate = new Date();
        } else {
            System.out.println("Not enough stock available.");
        }
    }

    // Update stock quantity
    public void updateStockQuantity(int newQuantity) {
        this.quantityInStock = newQuantity;
        this.lastStockUpdate = new Date();
    }

    // Check product availability
    public boolean isProductAvailable(int quantityToCheck) {
        return quantityInStock >= quantityToCheck;
    }

    // Calculate inventory value (total price of stock)
    public double getInventoryValue() {
        return product.getPrice() * quantityInStock;
    }

    // Check for low stock
    public boolean isLowStock(int threshold) {
        return quantityInStock < threshold;
    }

    // Check if product is out of stock
    public boolean isOutOfStock() {
        return quantityInStock == 0;
    }

    // Display inventory details
    public void displayInventoryDetails() {
        System.out.println("Inventory ID: " + inventoryID);
        System.out.println("Product: " + product.getProductDetails()); 
        System.out.println("Stock: " + quantityInStock);
        System.out.println("Last Update: " + lastStockUpdate);
    }

    // List low stock products based on a given threshold
    public static void listLowStockProducts(List<Inventory> inventoryList, int threshold) {
        System.out.println("Low Stock Products (Threshold: " + threshold + "):");
        for (Inventory inventory : inventoryList) {
            if (inventory.isLowStock(threshold)) {
                System.out.println(inventory.getProduct().getProductDetails() + " - Stock: " + inventory.getQuantityInStock());
            }
        }
    }

    // List out-of-stock products
    public static void listOutOfStockProducts(List<Inventory> inventoryList) {
        System.out.println("Out of Stock Products:");
        for (Inventory inventory : inventoryList) {
            if (inventory.isOutOfStock()) {
                System.out.println(inventory.getProduct().getProductDetails());
            }
        }
    }

    // List all products in inventory with stock levels
    public static void listAllProducts(List<Inventory> inventoryList) {
        System.out.println("All Products in Inventory:");
        for (Inventory inventory : inventoryList) {
            System.out.println(inventory.getProduct().getProductDetails() + " - Stock: " + inventory.getQuantityInStock());
        }
    }
}



