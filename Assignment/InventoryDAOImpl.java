package dao.impl;

import dao.InventoryDAO;
import entity.Inventory;
import exception.DatabaseConnectionException;
import exception.InventoryNotFoundException;
import exception.InvalidInventoryDataException;
import util.DBConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAOImpl implements InventoryDAO {

    @Override
    public void addInventory(Inventory inventory) throws InvalidInventoryDataException, DatabaseConnectionException {
        if (inventory == null) {
            throw new InvalidInventoryDataException("Inventory object cannot be null");
        }

        String sql = "INSERT INTO Inventory (ProductID, QuantityInStock, LastStockUpdate) VALUES (?, ?, ?)";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, inventory.getProductID());
            stmt.setInt(2, inventory.getQuantityInStock());
            stmt.setTimestamp(3, Timestamp.valueOf(inventory.getLastStockUpdate()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to add inventory", e);
        }
    }

    @Override
    public Inventory getInventoryById(int inventoryID) throws InventoryNotFoundException, DatabaseConnectionException {
        String sql = "SELECT * FROM Inventory WHERE InventoryID = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, inventoryID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Inventory(
                        rs.getInt("InventoryID"),
                        rs.getInt("ProductID"),
                        rs.getInt("QuantityInStock"),
                        rs.getTimestamp("LastStockUpdate").toLocalDateTime()
                );
            } else {
                throw new InventoryNotFoundException("Inventory with ID " + inventoryID + " not found.");
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to fetch inventory", e);
        }
    }

    @Override
    public List<Inventory> getAllInventory() throws DatabaseConnectionException {
        List<Inventory> inventories = new ArrayList<>();
        String sql = "SELECT * FROM Inventory";
        try (Connection conn = DBConnectionUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                inventories.add(new Inventory(
                        rs.getInt("InventoryID"),
                        rs.getInt("ProductID"),
                        rs.getInt("QuantityInStock"),
                        rs.getTimestamp("LastStockUpdate").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to fetch inventory list", e);
        }
        return inventories;
    }

    @Override
    public void updateInventory(Inventory inventory) throws InvalidInventoryDataException, DatabaseConnectionException {
        if (inventory == null) {
            throw new InvalidInventoryDataException("Inventory object cannot be null");
        }

        String sql = "UPDATE Inventory SET ProductID = ?, QuantityInStock = ?, LastStockUpdate = ? WHERE InventoryID = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, inventory.getProductID());
            stmt.setInt(2, inventory.getQuantityInStock());
            stmt.setTimestamp(3, Timestamp.valueOf(inventory.getLastStockUpdate()));
            stmt.setInt(4, inventory.getInventoryID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to update inventory", e);
        }
    }

    @Override
    public void deleteInventory(int inventoryID) throws InventoryNotFoundException, DatabaseConnectionException {
        String sql = "DELETE FROM Inventory WHERE InventoryID = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, inventoryID);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new InventoryNotFoundException("Inventory with ID " + inventoryID + " not found for deletion.");
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to delete inventory", e);
        }
    }
}
