package entity;

public class Product {
    private int productID;
    private String productName;
    private String description;
    private double price;
    private String category;

    public Product() {}

    public Product(int productID, String productName, String description, double price , String category) {
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public int getProductID() { return productID; }
    public void setProductID(int productID) { this.productID = productID; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }


    @Override
    public String toString() {
        return "Product{" +
               "productID=" + productID +
               ", productName='" + productName + '\'' +
               ", description='" + description + '\'' +
               ", price=" + price +
               '}';
    }
}
