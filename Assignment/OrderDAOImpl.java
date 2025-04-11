package com.gadgets.dao;

import com.gadgets.Customer;
import com.gadgets.Order;
import com.gadgets.util.DBConnectionUtil;
import com.gadgets.exception.InvalidInputException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public void addOrder(Order order) {
        String sql = "INSERT INTO orders (order_id, customer_id, order_date, total_amount, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, order.getOrderID());
            stmt.setInt(2, order.getCustomer().getCustomerID());
            stmt.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
            stmt.setDouble(4, order.getTotalAmount());
            stmt.setString(5, order.getStatus());

            stmt.executeUpdate();
            System.out.println("✅ Order added to database.");
        } catch (Exception e) {
            System.out.println("❌ Failed to insert order: " + e.getMessage());
        }
    }

    @Override
    public Order getOrderById(int orderId) {
        String sql = "SELECT * FROM orders WHERE order_id = ?";

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                
                Customer dummyCustomer = new Customer(rs.getInt("customer_id"), "First", "Last", "email@example.com");
                return new Order(
                        rs.getInt("order_id"),
                        dummyCustomer,
                        rs.getDate("order_date"),
                        rs.getDouble("total_amount")
                );
            }
        } catch (InvalidInputException e) {
            System.out.println("❌ Invalid input: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Failed to get order: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";

        try (Connection conn = DBConnectionUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
            	Customer dummyCustomer = new Customer(
            		    rs.getInt("customer_id"),
            		    "First",   	    "Last",           
            		    "test@example.com" 
            		);

            		// Then update phone and address with dummy values
            		dummyCustomer.updateCustomerInfo(
            		    "test@example.com", 
            		    "9876543210",      
            		    "Dummy Address"    
            		);


                Order order = new Order(
                        rs.getInt("order_id"),
                        dummyCustomer,
                        rs.getDate("order_date"),
                        rs.getDouble("total_amount")
                );
                order.setStatus(rs.getString("status"));
                orders.add(order);
            }

        } catch (InvalidInputException e) {
            System.out.println("❌ Invalid input: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Failed to get orders: " + e.getMessage());
        }
        return orders;
    }

    @Override
    public void updateOrderStatus(int orderId, String status) {
        String sql = "UPDATE orders SET status = ? WHERE order_id = ?";

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, orderId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Order status updated.");
            } else {
                System.out.println("⚠️ No order found with ID " + orderId);
            }

        } catch (Exception e) {
            System.out.println("❌ Failed to update status: " + e.getMessage());
        }
    }

    @Override
    public void cancelOrder(int orderId) {
        updateOrderStatus(orderId, "Cancelled");
    }
}
