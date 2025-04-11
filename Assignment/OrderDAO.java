package com.gadgets.dao;

import com.gadgets.Order;
import java.util.List;

public interface OrderDAO {
    void addOrder(Order order);
    Order getOrderById(int orderId);
    List<Order> getAllOrders();
    void updateOrderStatus(int orderId, String status);
    void cancelOrder(int orderId); // this would handle setting status to "Cancelled"
}

