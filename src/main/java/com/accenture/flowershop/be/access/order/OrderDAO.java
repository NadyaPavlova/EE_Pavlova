package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.entity.order.Order;

import java.util.List;

public interface OrderDAO {
    void saveOrder(Order order);
    List<Order> getAllOrders();
}
