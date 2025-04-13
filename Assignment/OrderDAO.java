package dao;

import entity.Order;
import exception.DatabaseConnectionException;
import exception.OrderProcessingException;

import java.util.List;

public interface OrderDAO {
    void addOrder(Order order) throws OrderProcessingException, DatabaseConnectionException;
    Order getOrderById(int orderId) throws DatabaseConnectionException;
    List<Order> getAllOrders() throws DatabaseConnectionException;
    List<Order> getOrdersByCustomerID(int customerID) throws DatabaseConnectionException;
    void updateOrder(Order order) throws OrderProcessingException, DatabaseConnectionException;
    void deleteOrder(int orderId) throws OrderProcessingException, DatabaseConnectionException;

 
    void updateOrderStatus(int orderID, String status) throws OrderProcessingException, DatabaseConnectionException;
    String getOrderStatus(int orderID) throws DatabaseConnectionException;
}

