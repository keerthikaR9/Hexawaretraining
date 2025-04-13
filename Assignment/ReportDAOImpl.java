package dao.impl;


import dao.ReportDAO;
import entity.Sale;
import exception.DatabaseConnectionException;
import util.DBConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDAOImpl implements ReportDAO {

    private Connection connection;

    // Constructor to initialize the connection
    public ReportDAOImpl() {
        this.connection = connection;
    }

    @Override
    public double getTotalSales() throws SQLException {
        String query = "SELECT SUM(TotalAmount) AS TotalSales FROM Orders";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("TotalSales");
            }
        }
        return 0.0;
    }

    @Override
    public int getTotalOrders() throws SQLException {
        String query = "SELECT COUNT(*) AS TotalOrders FROM Orders";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("TotalOrders");
            }
        }
        return 0;
    }

    @Override
    public String getMostSoldProduct() throws SQLException {
        String query = "SELECT p.ProductName, SUM(od.Quantity) AS QuantitySold FROM OrderDetails od " +
                       "JOIN Products p ON od.ProductID = p.ProductID " +
                       "GROUP BY p.ProductName ORDER BY QuantitySold DESC LIMIT 1";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("ProductName");
            }
        }
        return "";
    }

    @Override
    public List<Sale> generateSalesReport(String startDate, String endDate) throws DatabaseConnectionException {
        List<Sale> sales = new ArrayList<>();
        String query = "SELECT o.OrderID, p.ProductName, od.Quantity, o.TotalAmount, o.OrderDate " +
                       "FROM Orders o " +
                       "JOIN OrderDetails od ON o.OrderID = od.OrderID " +
                       "JOIN Products p ON od.ProductID = p.ProductID " +
                       "WHERE o.OrderDate BETWEEN ? AND ?";

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, startDate);
            stmt.setString(2, endDate);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt("OrderID");
                String productName = rs.getString("ProductName");
                int quantity = rs.getInt("Quantity");
                double totalAmount = rs.getDouble("TotalAmount");
                String saleDate = rs.getString("OrderDate");

                sales.add(new Sale(orderId, productName, quantity, totalAmount, saleDate));
            }

        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to fetch sales report.", e);
        }

        return sales;
    }


	
    
}

