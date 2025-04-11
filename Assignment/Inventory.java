package com.gadgets;

import java.util.Date;
import java.util.List;
import com.gadgets.exception.InsufficientStockException;
import com.gadgets.exception.InvalidInputException;
import com.gadgets.exception.OrderProcessingException;

public class Inventory {
    private int inventoryID;
    private Product product; // Composition
    private int quantityInStock;
    private Date lastStockUpdate;

    // Constructor
    public Inventory(int inventoryID, Product product, int quantityInStock) throws InvalidInputException {
        this.inventoryID = inventoryID;
        this.product = product;
        setQuantityInStock(quantityInStock); // Use setter for validation
        this.lastStockUpdate = new Date(); // Set current date
    }

    // Getters
    public int getInventoryID() {
        return inventoryID;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public Date getLastStockUpdate() {
        return lastStockUpdate;
    }

    // Setter with InvalidInputException
    public void setQuantityInStock(int quantityInStock) throws InvalidInputException {
        if (quantityInStock >= 0) {
            this.quantityInStock = quantityInStock;
            this.lastStockUpdate = new Date(); // Update timestamp
        } else {
            throw new InvalidInputException("❌ Stock quantity cannot be negative.");
        }
    }

    // Add stock with InvalidInputException
    public void addToInventory(int quantity) throws InvalidInputException {
        if (quantity > 0) {
            setQuantityInStock(this.quantityInStock + quantity);
        } else {
            throw new InvalidInputException("❌ Invalid quantity. Must be greater than 0.");
        }
    }

    // Remove stock with both InvalidInputException and InsufficientStockException
    public void removeFromInventory(int quantity) throws InvalidInputException, InsufficientStockException {
        if (quantity <= 0) {
            throw new InvalidInputException("❌ Quantity must be greater than 0.");
        }
        if (quantity > quantityInStock) {
            throw new InsufficientStockException("❌ Not enough stock. Available: " + quantityInStock + ", Requested: " + quantity);
        }
        setQuantityInStock(this.quantityInStock - quantity);
    }

    // Check product availability
    public boolean isProductAvailable(int quantityToCheck) {
        return quantityInStock >= quantityToCheck;
    }

    // Get inventory value with OrderProcessingException (if product is null)
    public double getInventoryValue() throws OrderProcessingException {
        if (product == null) {
            throw new OrderProcessingException("❌ Product data is missing. Cannot calculate inventory value.");
        }
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

    // List low stock products
    public static void listLowStockProducts(List<Inventory> inventoryList, int threshold) {
        System.out.println("Low Stock Products (Threshold: " + threshold + "):");
        inventoryList.stream()
                .filter(inventory -> inventory.isLowStock(threshold))
                .forEach(inventory -> System.out.println(
                        inventory.getProduct().getProductDetails() + " - Stock: " + inventory.getQuantityInStock()
                ));
    }

    // List out-of-stock products
    public static void listOutOfStockProducts(List<Inventory> inventoryList) {
        System.out.println("Out of Stock Products:");
        inventoryList.stream()
                .filter(Inventory::isOutOfStock)
                .forEach(inventory -> System.out.println(inventory.getProduct().getProductDetails()));
    }

    // List all products with stock levels
    public static void listAllProducts(List<Inventory> inventoryList) {
        System.out.println("All Products in Inventory:");
        inventoryList.forEach(inventory -> 
                System.out.println(inventory.getProduct().getProductDetails() + " - Stock: " + inventory.getQuantityInStock())
        );
    }
}
