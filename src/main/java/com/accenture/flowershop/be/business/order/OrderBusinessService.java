package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.be.entity.order.Order;

import java.util.List;

public interface OrderBusinessService {
    void addOrder(Order order);
    List<Order> getAllOrders();
    void payOrder(Order order);
    Order getOrderById(Long id);
}
