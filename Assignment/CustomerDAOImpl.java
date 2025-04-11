package com.gadgets.dao;

import com.gadgets.Customer;
import com.gadgets.util.DBConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO customers (customer_id, first_name, last_name, email, phone, address) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customer.getCustomerID());
            stmt.setString(2, customer.getFirstName());
            stmt.setString(3, customer.getLastName());
            stmt.setString(4, customer.getEmail());
            stmt.setString(5, customer.getPhone());
            stmt.setString(6, customer.getAddress());

            stmt.executeUpdate();
            System.out.println("✅ Customer added successfully.");
        } catch (Exception e) {
            System.out.println("❌ Failed to add customer: " + e.getMessage());
        }
    }

    public Customer getCustomerById(int id) {
        String sql = "SELECT * FROM customers WHERE customer_id=?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer(
                    rs.getInt("customer_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("address")
                );
            }
        } catch (Exception e) {
            System.out.println("❌ Retrieval failed: " + e.getMessage());
        }
        return null;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        try (Connection conn = DBConnectionUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Customer(
                    rs.getInt("customer_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("address")
                ));
            }
        } catch (Exception e) {
            System.out.println("❌ Error fetching customers: " + e.getMessage());
        }
        return list;
    }
}

