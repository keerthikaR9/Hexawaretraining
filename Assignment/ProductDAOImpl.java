package dao.impl;

import dao.ProductDAO;
import entity.Product;
import exception.DatabaseConnectionException;
import exception.InvalidDataException;
import util.DBConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public void addProduct(Product product) throws InvalidDataException, DatabaseConnectionException {
        if (product.getPrice() < 0 || product.getProductName() == null || product.getProductName().isEmpty()) {
            throw new InvalidDataException("Invalid product data: Product name is empty or price is negative.");
        }

        String sql = "INSERT INTO Products (ProductName, Description, Price, Category) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getProductName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setString(4, product.getCategory());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to add product to database.", e);
        }
    }

    @Override
    public Product getProductById(int productID) throws DatabaseConnectionException {
        String sql = "SELECT * FROM Products WHERE ProductID = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Product(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getString("Description"),
                        rs.getDouble("Price"),
                        rs.getString("Category")
                );
            }

        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to retrieve product.", e);
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() throws DatabaseConnectionException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Products";

        try (Connection conn = DBConnectionUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getString("Description"),
                        rs.getDouble("Price"),
                        rs.getString("Category")
                ));
            }

        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to fetch product list.", e);
        }
        return products;
    }

    @Override
    public void updateProduct(Product product) throws InvalidDataException, DatabaseConnectionException {
        if (product.getPrice() < 0 || product.getProductName() == null || product.getProductName().isEmpty()) {
            throw new InvalidDataException("Invalid product data for update.");
        }

        String sql = "UPDATE Products SET ProductName = ?, Description = ?, Price = ?, Category = ? WHERE ProductID = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getProductName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setString(4, product.getCategory());
            stmt.setInt(5, product.getProductID());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to update product.", e);
        }
    }

    @Override
    public void deleteProduct(int productID) throws DatabaseConnectionException {
        String sql = "DELETE FROM Products WHERE ProductID = ?";

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productID);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to delete product.", e);
        }
    }

    @Override
    public List<Product> searchProductsByName(String name) throws DatabaseConnectionException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE ProductName LIKE ?";

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                products.add(new Product(
                    rs.getInt("ProductID"),
                    rs.getString("ProductName"),
                    rs.getString("Description"),
                    rs.getDouble("Price"),
                    rs.getString("Category")
                ));
            }

        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to search products by name.", e);
        }

        return products;
    }


    @Override
    public List<Product> searchProductsByCategory(String category) throws DatabaseConnectionException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE Category = ?";

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, category);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                products.add(new Product(
                    rs.getInt("ProductID"),
                    rs.getString("ProductName"),
                    rs.getString("Description"),
                    rs.getDouble("Price"),
                    rs.getString("Category")
                ));
            }

        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to search products by category.", e);
        }

        return products;
    }


    @Override
    public List<Product> getRecommendedProducts() throws DatabaseConnectionException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Products ORDER BY Price ASC LIMIT 5";

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                products.add(new Product(
                    rs.getInt("ProductID"),
                    rs.getString("ProductName"),
                    rs.getString("Description"),
                    rs.getDouble("Price"),
                    rs.getString("Category")
                ));
            }

        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to fetch recommended products.", e);
        }

        return products;
    }

}
