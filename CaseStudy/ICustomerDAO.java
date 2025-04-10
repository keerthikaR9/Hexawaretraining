package dao;

import java.sql.SQLException;
import java.util.List;

import entity.Customer;

public interface ICustomerDAO {
    Customer getCustomerById(int customerId);
    Customer getCustomerByUsername(String username);
    boolean registerCustomer(Customer customer) throws SQLException;

    boolean updateCustomer(Customer customer);
    boolean deleteCustomer(int customerId);
    List<Customer> getAllCustomers();
}
