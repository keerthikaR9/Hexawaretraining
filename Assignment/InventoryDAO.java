package dao;

import entity.Inventory;
import exception.*;

import java.util.List;

public interface InventoryDAO {
    void addInventory(Inventory inventory) throws InvalidInventoryDataException, DatabaseConnectionException;
    Inventory getInventoryById(int inventoryID) throws InventoryNotFoundException, DatabaseConnectionException;
    List<Inventory> getAllInventory() throws DatabaseConnectionException;
    void updateInventory(Inventory inventory) throws InvalidInventoryDataException, DatabaseConnectionException;
    void deleteInventory(int inventoryID) throws InventoryNotFoundException, DatabaseConnectionException;
}
