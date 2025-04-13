package dao.impl;

import dao.OrderDAO;
import entity.Order;
import exception.DatabaseConnectionException;
import exception.OrderProcessingException;
import util.DBConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public void addOrder(Order order) throws OrderProcessingException, DatabaseConnectionException {
        String sql = "INSERT INTO Orders (CustomerID, OrderDate, TotalAmount, Status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, order.getCustomerID());
            stmt.setTimestamp(2, Timestamp.valueOf(order.getOrderDate()));
            stmt.setDouble(3, order.getTotalAmount());
            stmt.setString(4, order.getStatus()); 

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new OrderProcessingException("Failed to add order", e);
        } catch (Exception e) {
            throw new DatabaseConnectionException("Database connection error", e);
        }
    }

    @Override
    public Order getOrderById(int orderID) throws DatabaseConnectionException {
        String sql = "SELECT * FROM Orders WHERE OrderID = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Order(
                        rs.getInt("OrderID"),
                        rs.getInt("CustomerID"),
                        rs.getTimestamp("OrderDate").toLocalDateTime(),
                        rs.getDouble("TotalAmount"),
                        rs.getString("Status")  // Get the status
                );
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to retrieve order by ID", e);
        }
        return null;
    }

    @Override
    public List<Order> getOrdersByCustomerID(int customerID) throws DatabaseConnectionException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM Orders WHERE CustomerID = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("OrderID"),
                        rs.getInt("CustomerID"),
                        rs.getTimestamp("OrderDate").toLocalDateTime(),
                        rs.getDouble("TotalAmount"),
                        rs.getString("Status")  // Get the status
                ));
            }

        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to retrieve orders by customer ID", e);
        }
        return orders;
    }

    @Override
    public List<Order> getAllOrders() throws DatabaseConnectionException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM Orders";

        try (Connection conn = DBConnectionUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("OrderID"),
                        rs.getInt("CustomerID"),
                        rs.getTimestamp("OrderDate").toLocalDateTime(),
                        rs.getDouble("TotalAmount"),
                        rs.getString("Status")  // Get the status
                ));
            }

        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to retrieve all orders", e);
        }

        return orders;
    }

    @Override
    public void updateOrder(Order order) throws OrderProcessingException, DatabaseConnectionException {
        String sql = "UPDATE Orders SET CustomerID = ?, OrderDate = ?, TotalAmount = ?, Status = ? WHERE OrderID = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, order.getCustomerID());
            stmt.setTimestamp(2, Timestamp.valueOf(order.getOrderDate()));
            stmt.setDouble(3, order.getTotalAmount());
            stmt.setString(4, order.getStatus());  // Update the status
            stmt.setInt(5, order.getOrderID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new OrderProcessingException("Failed to update order", e);
        } catch (Exception e) {
            throw new DatabaseConnectionException("Database connection error during update", e);
        }
    }

    @Override
    public void deleteOrder(int orderID) throws OrderProcessingException, DatabaseConnectionException {
        String sql = "DELETE FROM Orders WHERE OrderID = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new OrderProcessingException("Failed to delete order", e);
        } catch (Exception e) {
            throw new DatabaseConnectionException("Database connection error during deletion", e);
        }
    }

    @Override
    public void updateOrderStatus(int orderID, String status) throws OrderProcessingException, DatabaseConnectionException {
        String sql = "UPDATE Orders SET Status = ? WHERE OrderID = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, orderID);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new OrderProcessingException("Failed to update order status", e);
        } catch (Exception e) {
            throw new DatabaseConnectionException("Database connection error while updating status", e);
        }
    }

    @Override
    public String getOrderStatus(int orderID) throws DatabaseConnectionException {
        String sql = "SELECT Status FROM Orders WHERE OrderID = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("Status");
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to retrieve order status", e);
        }
        return null;
    }

}
