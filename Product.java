package com.gadgets;

public class Product {
    private int productID;
    private String productName;
    private String description;
    private double price;
    private int inStock;

    // Constructor
    public Product(int productID, String productName, String description, double price, int inStock) {
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.inStock = inStock;
    }

    // Getter for Product ID
    public int getProductID() {
        return productID;
    }

    // Getter for Product Name
    public String getProductName() {
        return productName;
    }

    // Getter for Description
    public String getDescription() {
        return description;
    }

    // Getter for Price
    public double getPrice() {
        return price;
    }

    // Getter for Stock Quantity
    public int getStockQuantity() {
        return inStock;
    }

    // Method to get product details
    public String getProductDetails() {  
        return "Product ID: " + productID + ", Name: " + productName +
               ", Description: " + description + ", Price: $" + price +
               ", In Stock: " + inStock;
    }

    // Method to update product details
    public void updateProductInfo(double price, String description) {
        this.price = price;
        this.description = description;
    }

    // Method to check stock availability
    public boolean isProductInStock() {
        return inStock > 0;
    }

    // Reduce stock when a product is purchased
    public boolean reduceStock(int quantity) {
        if (quantity <= inStock) {
            inStock -= quantity;
            return true; // Successfully reduced stock
        } else {
            System.out.println("Not enough stock available.");
            return false; // Stock not sufficient
        }
    }

    // Add stock when restocking or canceling an order
    public void addStock(int quantity) {
        inStock += quantity;
    }
}

