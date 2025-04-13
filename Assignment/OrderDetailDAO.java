package dao;

import java.util.List;

import entity.OrderDetail;
import exception.DatabaseConnectionException;
import exception.InvalidOrderDetailException;
import exception.OrderDetailNotFoundException;

public interface OrderDetailDAO {
    void addOrderDetail(OrderDetail orderDetail) throws DatabaseConnectionException, InvalidOrderDetailException;
    List<OrderDetail> getOrderDetailsByOrderID(int orderID) throws DatabaseConnectionException, OrderDetailNotFoundException;
    void updateOrderDetail(OrderDetail orderDetail) throws DatabaseConnectionException, OrderDetailNotFoundException, InvalidOrderDetailException;
    void deleteOrderDetail(int orderDetailID) throws DatabaseConnectionException, OrderDetailNotFoundException;
}
