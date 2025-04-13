package dao.impl;

import dao.CustomerDAO;
import entity.Customer;
import exception.InvalidDataException;
import exception.DatabaseConnectionException;
import util.DBConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public void addCustomer(Customer customer) throws InvalidDataException, DatabaseConnectionException {
        validateCustomer(customer);

        String sql = "INSERT INTO Customers (FirstName, LastName, Email, Phone, Address) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getPhone());
            stmt.setString(5, customer.getAddress());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to add customer to the database", e);
        }
    }

    @Override
    public Customer getCustomerById(int customerId) throws DatabaseConnectionException {
        String sql = "SELECT * FROM Customers WHERE CustomerID = ?";
        Customer customer = null;

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                customer = new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Email"),
                        rs.getString("Phone"),
                        rs.getString("Address")
                );
            }

        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to retrieve customer from the database", e);
        }

        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() throws DatabaseConnectionException {
        String sql = "SELECT * FROM Customers";
        List<Customer> customers = new ArrayList<>();

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Email"),
                        rs.getString("Phone"),
                        rs.getString("Address")
                );
                customers.add(customer);
            }

        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to fetch all customers", e);
        }

        return customers;
    }

    @Override
    public void updateCustomer(Customer customer) throws InvalidDataException, DatabaseConnectionException {
        validateCustomer(customer);

        String sql = "UPDATE Customers SET FirstName=?, LastName=?, Email=?, Phone=?, Address=? WHERE CustomerID=?";

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getPhone());
            stmt.setString(5, customer.getAddress());
            stmt.setInt(6, customer.getCustomerID());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to update customer details", e);
        }
    }

    @Override
    public void deleteCustomer(int customerId) throws DatabaseConnectionException {
        String sql = "DELETE FROM Customers WHERE CustomerID=?";

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to delete customer", e);
        }
    }

    // ========== Private Helper ==========
    private void validateCustomer(Customer customer) throws InvalidDataException {
        if (customer.getFirstName() == null || customer.getFirstName().trim().isEmpty()) {
            throw new InvalidDataException("First name cannot be empty.");
        }
        if (customer.getEmail() == null || !customer.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new InvalidDataException("Invalid email format: " + customer.getEmail());
        }
        if (customer.getPhone() == null || !customer.getPhone().matches("\\d{10}")) {
            throw new InvalidDataException("Phone number must be exactly 10 digits.");
        }
    }
}
