package dao.impl;

import dao.OrderDetailDAO;
import entity.OrderDetail;
import exception.DatabaseConnectionException;
import exception.InvalidOrderDetailException;
import exception.OrderDetailNotFoundException;
import util.DBConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    @Override
    public void addOrderDetail(OrderDetail orderDetail) throws DatabaseConnectionException, InvalidOrderDetailException {
        if (orderDetail == null || orderDetail.getQuantity() <= 0) {
            throw new InvalidOrderDetailException("Order detail is invalid or quantity is zero/negative.");
        }

        String sql = "INSERT INTO OrderDetail (OrderID, ProductID, Quantity) VALUES (?, ?, ?)";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderDetail.getOrderID());
            stmt.setInt(2, orderDetail.getProductID());
            stmt.setInt(3, orderDetail.getQuantity());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to add order detail: " + e.getMessage());
        }
    }

    @Override
    public List<OrderDetail> getOrderDetailsByOrderID(int orderID) throws DatabaseConnectionException, OrderDetailNotFoundException {
        List<OrderDetail> details = new ArrayList<>();
        String sql = "SELECT * FROM OrderDetail WHERE OrderID = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                OrderDetail detail = new OrderDetail(
                        rs.getInt("OrderDetailID"),
                        rs.getInt("OrderID"),
                        rs.getInt("ProductID"),
                        rs.getInt("Quantity")
                );
                details.add(detail);
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to fetch order details: " + e.getMessage());
        }

        if (details.isEmpty()) {
            throw new OrderDetailNotFoundException("No order details found for order ID: " + orderID);
        }

        return details;
    }

    @Override
    public void updateOrderDetail(OrderDetail orderDetail) throws DatabaseConnectionException, OrderDetailNotFoundException, InvalidOrderDetailException {
        if (orderDetail == null || orderDetail.getQuantity() <= 0) {
            throw new InvalidOrderDetailException("Invalid order detail data provided.");
        }

        String sql = "UPDATE OrderDetail SET OrderID = ?, ProductID = ?, Quantity = ? WHERE OrderDetailID = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderDetail.getOrderID());
            stmt.setInt(2, orderDetail.getProductID());
            stmt.setInt(3, orderDetail.getQuantity());
            stmt.setInt(4, orderDetail.getOrderDetailID());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new OrderDetailNotFoundException("OrderDetail ID not found: " + orderDetail.getOrderDetailID());
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to update order detail: " + e.getMessage());
        }
    }

    @Override
    public void deleteOrderDetail(int orderDetailID) throws DatabaseConnectionException, OrderDetailNotFoundException {
        String sql = "DELETE FROM OrderDetail WHERE OrderDetailID = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderDetailID);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new OrderDetailNotFoundException("OrderDetail ID not found: " + orderDetailID);
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to delete order detail: " + e.getMessage());
        }
    }
}
