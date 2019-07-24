package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.entity.order.Order;

import java.util.List;

public interface OrderDAO {
    void saveOrder(Order order);
    List<Order> getAllOrders();
    void updateStatus(Order order);
    Order getOrderById(Long id);
    List<Order> getAllOrdersUser(Long id);
    void updateClosingDate (Order order);
}
