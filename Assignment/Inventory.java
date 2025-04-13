package entity;

import java.time.LocalDateTime;

public class Inventory {
    private int inventoryID;
    private int productID;
    private int quantityInStock;
    private LocalDateTime lastStockUpdate;

    public Inventory() {}

    public Inventory(int inventoryID, int productID, int quantityInStock, LocalDateTime lastStockUpdate) {
        this.inventoryID = inventoryID;
        this.productID = productID;
        this.quantityInStock = quantityInStock;
        this.lastStockUpdate = lastStockUpdate;
    }

    public int getInventoryID() { return inventoryID; }
    public void setInventoryID(int inventoryID) { this.inventoryID = inventoryID; }

    public int getProductID() { return productID; }
    public void setProductID(int productID) { this.productID = productID; }

    public int getQuantityInStock() { return quantityInStock; }
    public void setQuantityInStock(int quantityInStock) { this.quantityInStock = quantityInStock; }

    public LocalDateTime getLastStockUpdate() { return lastStockUpdate; }
    public void setLastStockUpdate(LocalDateTime lastStockUpdate) { this.lastStockUpdate = lastStockUpdate; }

    @Override
    public String toString() {
        return "Inventory{" +
               "inventoryID=" + inventoryID +
               ", productID=" + productID +
               ", quantityInStock=" + quantityInStock +
               ", lastStockUpdate=" + lastStockUpdate +
               '}';
    }
}
