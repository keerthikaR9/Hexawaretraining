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
        setPrice(price);
        setStockQuantity(inStock);
    }

    // Getters
    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return inStock;
    }

    // Setters with validation
    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            System.out.println("Price cannot be negative.");
        }
    }

    public void setStockQuantity(int inStock) {
        if (inStock >= 0) {
            this.inStock = inStock;
        } else {
            System.out.println("Stock quantity cannot be negative.");
        }
    }

    public void setDescription(String description) {
        if (!description.isEmpty()) {
            this.description = description;
        } else {
            System.out.println("Description cannot be empty.");
        }
    }

    // Method to get product details
    public String getProductDetails() {  
        return "Product ID: " + productID + ", Name: " + productName +
               ", Description: " + description + ", Price: $" + price +
               ", In Stock: " + inStock;
    }

    // Method to update product details
    public void updateProductInfo(double price, String description) {
        setPrice(price);
        setDescription(description);
    }

    // Method to check stock availability
    public boolean isProductInStock() {
        return inStock > 0;
    }

    // Reduce stock when a product is purchased
    public boolean reduceStock(int quantity) {
        if (quantity > 0 && quantity <= inStock) {
            inStock -= quantity;
            return true; // Successfully reduced stock
        } else {
            System.out.println("Invalid stock quantity or not enough stock available.");
            return false; // Stock not sufficient
        }
    }

    // Add stock when restocking or canceling an order
    public void addStock(int quantity) {
        if (quantity > 0) {
            inStock += quantity;
        } else {
            System.out.println("Stock addition must be positive.");
        }
    }
}


