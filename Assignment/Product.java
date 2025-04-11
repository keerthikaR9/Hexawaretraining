package com.gadgets;

import com.gadgets.exception.InvalidInputException;

public class Product {
    private int productID;
    private String productName;
    private String description;
    private double price;
    private int inStock;

    // Constructor
    public Product(int productID, String productName, String description, double price, int inStock) throws InvalidInputException {
        this.productID = productID;
        this.productName = productName;
        setDescription(description);
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
    public void setQuantityInStock(int quantityInStock) {
        this.inStock = quantityInStock;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setPrice(double price) throws InvalidInputException {
        if (price >= 0) {
            this.price = price;
        } else {
            throw new InvalidInputException("Price cannot be negative.");
        }
    }


    public void setStockQuantity(int inStock) throws InvalidInputException {
        if (inStock >= 0) {
            this.inStock = inStock;
        } else {
            throw new InvalidInputException("Stock quantity cannot be negative.");
        }
    }

    public void setDescription(String description) throws InvalidInputException  {
        if (!description.isEmpty()) {
            this.description = description;
        } else {
            throw new InvalidInputException("Description cannot be empty.");
        }
    }

    // Method to get product details
    public String getProductDetails() {  
        return "Product ID: " + productID + ", Name: " + productName +
               ", Description: " + description + ", Price: $" + price +
               ", In Stock: " + inStock;
    }
    public int getQuantityInStock() {
        return getQuantityInStock();
    }
    // Method to update product details
    
    public void updateProductInfo(double price, String description) throws InvalidInputException {
        setPrice(price);
        setDescription(description);
    }

    // Method to check stock availability
    public boolean isProductInStock() {
        return inStock > 0;
    }

    // Reduce stock when a product is purchased
    public void reduceStock(int quantity) {
        if (quantity > 0 && quantity <= inStock) {
            inStock -= quantity;
        } else {
            throw new IllegalArgumentException("Invalid quantity or not enough stock available.");
        }
    }

    // Add stock when restocking or canceling an order
    public void addStock(int quantity) {
        if (quantity > 0) {
            inStock += quantity;
        } else {
            throw new IllegalArgumentException("Stock addition must be positive.");
        }
    }
}
