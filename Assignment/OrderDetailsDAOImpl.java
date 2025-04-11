package com.gadgets.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gadgets.OrderDetail;
import com.gadgets.Product;
import com.gadgets.util.DBConnectionUtil;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {

    @Override
    public void addOrderDetail(OrderDetail orderDetail, int orderId) {
        String query = "INSERT INTO order_details (order_id, product_id, quantity, subtotal) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, orderId);
            stmt.setInt(2, orderDetail.getProduct().getProductID());
            stmt.setInt(3, orderDetail.getQuantity());
            stmt.setDouble(4, orderDetail.getSubtotal());

            stmt.executeUpdate();
            System.out.println("✅ Order detail inserted successfully!");

        } catch (SQLException e) {
            System.err.println("❌ Failed to insert order detail: " + e.getMessage());
        }
    }

    @Override
    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        String query = "SELECT * FROM order_details WHERE order_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Product product = new Product(rs.getInt("product_id")); // Assuming product can be fetched like this
                int quantity = rs.getInt("quantity");
                double subtotal = rs.getDouble("subtotal");

                OrderDetail detail = new OrderDetail(product, quantity);
                orderDetails.add(detail);
            }

        } catch (Exception e) {
            System.err.println("❌ Error fetching order details: " + e.getMessage());
        }

        return orderDetails;
    }
}
