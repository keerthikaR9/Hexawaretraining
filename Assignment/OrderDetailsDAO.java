package com.gadgets.dao;

import com.gadgets.OrderDetail;
import java.util.List;

public interface OrderDetailsDAO {
    void addOrderDetail(OrderDetail orderDetail, int orderId);
    List<OrderDetail> getOrderDetailsByOrderId(int orderId);
}

