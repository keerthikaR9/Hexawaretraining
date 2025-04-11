package com.gadgets.dao;

import com.gadgets.Product;
import com.gadgets.util.DBConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    public void addProduct(Product product) {
        String sql = "INSERT INTO products (product_id, name, description, price, stock) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, product.getProductID());
            stmt.setString(2, product.getProductName());
            stmt.setString(3, product.getDescription());
            stmt.setDouble(4, product.getPrice());
            stmt.setInt(5, product.getStockQuantity());

            stmt.executeUpdate();
            System.out.println("✅ Product inserted successfully.");
        } catch (Exception e) {
            System.out.println("❌ Insert failed: " + e.getMessage());
        }
    }

    public Product getProductById(int productId) {
        String sql = "SELECT * FROM products WHERE product_id = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Product(
                    rs.getInt("product_id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getInt("stock")
                );
            }
        } catch (Exception e) {
            System.out.println("❌ Retrieval failed: " + e.getMessage());
        }
        return null;
    }

    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Connection conn = DBConnectionUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Product(
                    rs.getInt("product_id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getInt("stock")
                ));
            }
        } catch (Exception e) {
            System.out.println("❌ Fetch all failed: " + e.getMessage());
        }
        return list;
    }

    public void updateProduct(Product product) {
        String sql = "UPDATE products SET name=?, description=?, price=?, stock=? WHERE product_id=?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getProductName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getStockQuantity());
            stmt.setInt(5, product.getProductID());

            stmt.executeUpdate();
            System.out.println("✅ Product updated.");
        } catch (Exception e) {
            System.out.println("❌ Update failed: " + e.getMessage());
        }
    }

    public void deleteProduct(int productId) {
        String sql = "DELETE FROM products WHERE product_id=?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            stmt.executeUpdate();
            System.out.println("✅ Product deleted.");
        } catch (Exception e) {
            System.out.println("❌ Delete failed: " + e.getMessage());
        }
    }
}
