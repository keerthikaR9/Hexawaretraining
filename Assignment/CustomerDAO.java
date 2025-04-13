package dao;

import entity.Customer;
import exception.InvalidDataException;
import exception.DatabaseConnectionException;

import java.util.List;

public interface CustomerDAO {
    void addCustomer(Customer customer) throws InvalidDataException, DatabaseConnectionException;

    Customer getCustomerById(int customerId) throws DatabaseConnectionException;

    List<Customer> getAllCustomers() throws DatabaseConnectionException;

    void updateCustomer(Customer customer) throws InvalidDataException, DatabaseConnectionException;

    void deleteCustomer(int customerId) throws DatabaseConnectionException;
}
